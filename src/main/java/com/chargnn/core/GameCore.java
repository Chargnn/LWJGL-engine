package com.chargnn.core;

import com.chargnn.Main;
import com.chargnn.entityObject.Camera;
import com.chargnn.entityObject.EntityModel;
import com.chargnn.shader.Shader;
import com.chargnn.shader.light.Light;
import com.chargnn.utils.readers.LoadOBJ;
import com.chargnn.utils.RenderUtil;
import org.joml.Vector3f;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
public class GameCore {

    private EntityModel entity;
    private Shader shader;
    private Camera camera;
    private Light light;

    public void init() throws ParserConfigurationException, SAXException, IOException {
        entity = new EntityModel(LoadOBJ.loadOBJ("res/stall.obj", null), new Vector3f(0, 0, -1), new Vector3f(0, 0 ,0), new Vector3f(1, 1, 1));
        shader = new Shader("res/shader/shader.vert", "res/shader/shader.frag");
        camera = new Camera(new Vector3f(0, 0, 0), Main.getDisplay(), shader);
        light = new Light(new Vector3f(0, -10, 0), new Vector3f(1, 1, 1), shader);
    }

    public void update(int delta){
        if(Main.getDisplay().hasResized()){
            camera.setProjectionMatrix(Main.getDisplay());
            Main.getDisplay().setHasResized(false);
        }

        RenderUtil.clearScreen(0, 0, 0, 1);

        entity.rotateEntity(0, (float) 0.0005 * delta, 0);
        camera.update(delta);
    }

    public void render() {
        shader.bind();

        entity.render(shader);
        light.render();

        shader.unbind();
    }
}
