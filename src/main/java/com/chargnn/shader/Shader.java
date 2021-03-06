package com.chargnn.shader;

import com.chargnn.utils.readers.SettingsManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Shader {

    private int programID;
    private UniformHandler uniformHandler;

    public Shader(String vertexShader, String fragmentShader) throws IOException, SAXException, ParserConfigurationException {
        programID = GL20.glCreateProgram();
        uniformHandler = new UniformHandler(this);

        try {
            createShader(loadShader(vertexShader), GL20.GL_VERTEX_SHADER);
            createShader(loadShader(fragmentShader), GL20.GL_FRAGMENT_SHADER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);

        bindUniforms();
    }

    private void bindUniforms() throws ParserConfigurationException, SAXException, IOException {
        // camera
        uniformHandler.addUniform("transformationMatrix");
        uniformHandler.addUniform("projectionMatrix");
        uniformHandler.addUniform("viewMatrix");

        uniformHandler.addUniform("lightPosition");
        uniformHandler.addUniform("lightColor");
    }

    public void bind(){
        GL20.glUseProgram(programID);
    }

    public void unbind(){
        GL20.glUseProgram(0);
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

    public int getProgramID(){
        return programID;
    }

    public UniformHandler getUniformHandler(){
        return uniformHandler;
    }
}
