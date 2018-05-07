package com.chargnn.core;

import com.chargnn.Main;
import com.chargnn.entityObject.Camera;
import com.chargnn.entityObject.EntityModel;
import com.chargnn.modelObject.Model;
import com.chargnn.modelObject.texture.Texture;
import com.chargnn.shader.Shader;
import com.chargnn.utils.RenderUtil;
import org.joml.Vector3f;

public class GameCore {

    float[] vertices = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f
    };

    float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0
    };

    int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22
    };

    private EntityModel entity = new EntityModel(new Model("exemple", Texture.loadTexture("res/test.jpg")), new Vector3f(0, 0, -1), new Vector3f(0, 0 ,0), new Vector3f(1, 1, 1));
    private Shader shader = new Shader("res/shader/shader.vert", "res/shader/shader.frag");
    private Camera camera = new Camera(new Vector3f(0, 0, 0), Main.getDisplay(), shader);

    public void init(){
        entity.getModel().pushVertices(vertices);
        entity.getModel().pushTextureCoords(textureCoords);
        entity.getModel().pushIndices(indices);
    }

    public void update(int delta){
        RenderUtil.clearScreen(0, 0, 0, 0);

        //entity.rotateEntity(0, (float) 0.005 * delta, 0);
        camera.update(delta);
    }

    public void render(){
        shader.bind();

        entity.render(shader);

        shader.unbind();
    }
}
