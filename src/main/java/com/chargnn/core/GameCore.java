package com.chargnn.core;

import com.chargnn.Main;
import com.chargnn.core.io.Keyboard;
import com.chargnn.entityObject.Camera;
import com.chargnn.entityObject.EntityModel;
import com.chargnn.modelObject.Model;
import com.chargnn.shader.Shader;
import com.chargnn.utils.RenderUtil;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class GameCore {

    private EntityModel entity = new EntityModel(new Model("exemple"), new Vector3f(0, 0, -1), new Vector3f(0, 0 ,0), new Vector3f(1, 1, 1));
    private Shader shader = new Shader("res/shader/shader.vert", "res/shader/shader.frag");
    private Camera camera = new Camera(new Vector3f(0, 0, 0), Main.getDisplay(), shader);

    public void init(){
        entity.getModel().pushVertices(new float[] { -0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f});
        entity.getModel().pushIndices(new int[] { 0,1,3,3,1,2});
    }

    public void update(int delta){
        RenderUtil.clearScreen(0, 0, 0, 0);

        entity.rotateEntity(0, (float ) 0.005 * delta, 0);
    }

    public void render(){
        shader.bind();

        entity.render(shader);

        shader.unbind();
    }
}
