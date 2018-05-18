package com.chargnn.entityObject;

import com.chargnn.modelObject.Model;
import com.chargnn.shader.Shader;
import com.chargnn.utils.Mathf;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class EntityModel extends Entity {

    private Model model;

    public EntityModel(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
        super(position, rotation, scale);
        this.model = model;
    }

    public void render(Shader shader){
        if(!model.getBindings().contains("vertices"))
            System.err.println("No vertices for id: [" + getModel().getId() + "]");

        initVertexArray();

        loadTransformationMatrix(shader);

        draw();

        closeVertexArray();
    }

    private void initVertexArray(){
        glBindVertexArray(getModel().getVaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, getModel().getTexture().getTextureID());
    }

    private void loadTransformationMatrix(Shader shader){
        Matrix4f transform = Mathf.createTransformationMatrix(this);
        shader.getUniformHandler().setUniformMat4("transformationMatrix", transform);
    }

    private void draw(){
        if(model.getBindings().contains("indices")) { // draw with indices
            glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        } else {                           // draw without indices
            glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
        }
    }

    private void closeVertexArray(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    public Model getModel(){return model;}

}
