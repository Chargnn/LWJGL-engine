package com.chargnn.entityObject;

import com.chargnn.modelObject.Model;
import com.chargnn.shader.Shader;
import com.chargnn.utils.Mathf;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Entity {

    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    private Model model;

    public Entity(Model model, Vector3f position, Vector3f rotation, Vector3f scale){
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void render(Shader shader){
        if(!getModel().getBindings().contains("vertices"))
            System.err.println("No vertices for id: [" + getModel().getId() + "]");

        GL30.glBindVertexArray(getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);

        Matrix4f transform = Mathf.createTransformationMatrix(this);
        shader.getUniformHandler().setUniformMat4("transformationMatrix", transform);

        if(getModel().getBindings().contains("indices")) { // draw with indices
            GL11.glDrawElements(GL11.GL_TRIANGLES, getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        } else {                           // draw without indices
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, getModel().getVertexCount());
        }

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public Model getModel(){
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }
}
