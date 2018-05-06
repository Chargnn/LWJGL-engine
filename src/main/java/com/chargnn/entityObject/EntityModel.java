package com.chargnn.entityObject;

import com.chargnn.modelObject.Model;
import com.chargnn.shader.Shader;
import com.chargnn.utils.Mathf;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class EntityModel extends Entity {

    private Model model;

    public EntityModel(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        this.model = model;
    }

    public void render(Shader shader){
        if(!model.getBindings().contains("vertices"))
            System.err.println("No vertices for id: [" + getModel().getId() + "]");

        GL30.glBindVertexArray(getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);

        Matrix4f transform = Mathf.createTransformationMatrix(this);
        shader.getUniformHandler().setUniformMat4("transformationMatrix", transform);

        if(model.getBindings().contains("indices")) { // draw with indices
            GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        } else {                           // draw without indices
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        }

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public Model getModel(){return model;}

}
