package com.chargnn.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.HashMap;

public class UniformHandler {

    private static FloatBuffer matrixBufferTemplate = BufferUtils.createFloatBuffer(4*4);
    private HashMap<String, Integer> uniforms = new HashMap<>();

    private Shader shader;


    public UniformHandler(Shader shader){
        this.shader = shader;
    }

    public void addUniform(String uniform){
        int location = GL20.glGetUniformLocation(shader.getProgramID(), uniform);

        if(location == -1){
            System.err.println("Could not find uniform location for: " + uniform);
            System.exit(1);
        }

        uniforms.put(uniform, location);
    }

    public void setUniformi(String uniformName, int value){
        if(uniforms.get(uniformName) == null){
            System.err.println("Could not find uniform location for: " + uniformName);
            System.exit(1);
        }

        GL20.glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniformf(String uniformName, float value){
        if(uniforms.get(uniformName) == null){
            System.err.println("Could not find uniform location for: " + uniformName);
            System.exit(1);
        }

        GL20.glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform3f(String uniformName, Vector3f value){
        if(uniforms.get(uniformName) == null){
            System.err.println("Could not find uniform location for: " + uniformName);
            System.exit(1);
        }

        GL20.glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
    }

    public void setUniformMat4(String uniformName, Matrix4f value){
        if(uniforms.get(uniformName) == null){
            System.err.println("Could not find uniform location for: " + uniformName);
            System.exit(1);
        }

        GL20.glUniformMatrix4fv(uniforms.get(uniformName), false, value.get(matrixBufferTemplate));
    }
}
