package com.chargnn.core;

import com.chargnn.entityObject.Entity;
import com.chargnn.modelObject.Model;
import com.chargnn.shader.Shader;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class GameCore {

    float[] vertices = {
            -0.5f, 0.5f, 0f,//v0
            -0.5f, -0.5f, 0f,//v1
            0.5f, -0.5f, 0f,//v2
            0.5f, 0.5f, 0f,//v3
    };

    int[] indices = {
            0,1,3,//top left triangle (v0, v1, v3)
            3,1,2//bottom right triangle (v3, v1, v2)
    };

    private Entity entity = new Entity(new Model("exemple"), new Vector3f(1, 0, 0), new Vector3f(0, 0 ,0), new Vector3f(1, 1, 1));
    private Shader shader = new Shader("res/shader/shader.vert", "res/shader/shader.frag");

    public void init(){
        entity.getModel().pushVertices(vertices);
        entity.getModel().pushIndices(indices);
    }

    public void update(int delta){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(0, 0, 0, 1);
    }

    public void render(){

        shader.bind();

        entity.getModel().render(entity, shader);

        shader.unbind();
    }
}
