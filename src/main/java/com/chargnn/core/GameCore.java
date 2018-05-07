package com.chargnn.core;

import com.chargnn.Main;
import com.chargnn.entityObject.Camera;
import com.chargnn.entityObject.EntityModel;
import com.chargnn.shader.Shader;
import com.chargnn.utils.LoadOBJ;
import com.chargnn.utils.RenderUtil;
import org.joml.Vector3f;

public class GameCore {

    private EntityModel entity;
    private Shader shader;
    private Camera camera;

    public void init(){
        entity = new EntityModel(LoadOBJ.loadOBJ("res/stall.obj", null), new Vector3f(0, 0, -1), new Vector3f(0, 0 ,0), new Vector3f(1, 1, 1));
        shader = new Shader("res/shader/shader.vert", "res/shader/shader.frag");
        camera = new Camera(new Vector3f(0, 0, 0), Main.getDisplay(), shader);
    }

    public void update(int delta){
        RenderUtil.clearScreen(0, 0, 0, 0);

        entity.rotateEntity(0, (float) 0.0005 * delta, 0);
        camera.update(delta);
    }

    public void render(){
        shader.bind();

        entity.render(shader);

        shader.unbind();
    }
}
