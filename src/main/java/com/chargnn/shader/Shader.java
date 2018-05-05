package com.chargnn.shader;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.*;
import java.util.HashMap;

public class Shader {

    private int programID;
    private HashMap<String, Integer> uniforms = new HashMap<>();

    public Shader(String vertexShader, String fragmentShader) {
        programID = GL20.glCreateProgram();

        try {
            createShader(loadShader(vertexShader), GL20.GL_VERTEX_SHADER);
            createShader(loadShader(fragmentShader), GL20.GL_FRAGMENT_SHADER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
    }

    public void bind(){
        GL20.glUseProgram(programID);
    }

    public void unbind(){
        GL20.glUseProgram(0);
    }

    public void addUniform(String uniform){
        int location = GL20.glGetUniformLocation(programID, uniform);

        if(location == -1){
            System.err.println("Could not find uniform location for: " + uniform);
            System.exit(1);
        }

        uniforms.put(uniform, location);
    }

    public void setUniformi(String uniformName, int value){
        GL20.glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniformf(String uniformName, float value){
        GL20.glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform3f(String uniformName, Vector3f value){
        GL20.glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
    }

    private void createShader(String src, int type){
        int shader = GL20.glCreateShader(type);

        GL20.glShaderSource(shader, src);
        GL20.glCompileShader(shader);

        if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println("Error compiling shader : " + GL20.glGetShaderInfoLog(shader));
            System.exit(1);
        }

        GL20.glAttachShader(programID, shader);
    }

    private String loadShader(String src) throws IOException {
        StringBuilder sb = new StringBuilder();

        InputStream is = new FileInputStream(new File(src));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        while((line = reader.readLine()) != null){
            sb.append(line + "\n");
        }

        reader.close();

        return sb.toString();
    }
}
