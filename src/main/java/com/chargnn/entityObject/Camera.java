package com.chargnn.entityObject;

import com.chargnn.shader.Shader;
import com.chargnn.utils.Display;
import com.chargnn.utils.Mathf;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera extends Entity{

    private Matrix4f projectionMatrix;

    public Camera(Vector3f position) {
        super(position, new Vector3f().zero(), new Vector3f().zero());
    }

    public void init(Display display, Shader shader){
        projectionMatrix = Mathf.createProjectionMatrix(this, display);
        shader.bind();

        shader.getUniformHandler().setUniformMat4("projectionMatrix", projectionMatrix);

        shader.unbind();
    }


}
