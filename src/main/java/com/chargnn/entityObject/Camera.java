package com.chargnn.entityObject;

import com.chargnn.core.io.Keyboard;
import com.chargnn.shader.Shader;
import com.chargnn.utils.Display;
import com.chargnn.utils.Mathf;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.security.Key;

public class Camera extends Entity{

    private Shader shader;
    private Matrix4f projectionMatrix;

    public Camera(Vector3f position, Display display, Shader shader) {
        super(position, new Vector3f().zero(), new Vector3f().zero());
        this.shader = shader;

        setProjectionMatrix(display);
    }

    public void setProjectionMatrix(Display display){
        projectionMatrix = Mathf.createProjectionMatrix(this, display);
        shader.bind();

        shader.getUniformHandler().setUniformMat4("projectionMatrix", projectionMatrix);

        shader.unbind();
    }

    public void update(int delta) {
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W))
            position.z -= 0.01f * delta;
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S))
            position.z += 0.01f * delta;
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A))
            position.x -= 0.01f * delta;
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D))
            position.x += 0.01f * delta;

        shader.bind();
        shader.getUniformHandler().setUniformMat4("viewMatrix", Mathf.createViewMatrix(this));
        shader.unbind();
    }


}
