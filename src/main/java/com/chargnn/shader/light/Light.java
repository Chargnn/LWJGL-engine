package com.chargnn.shader.light;

import com.chargnn.shader.Shader;
import org.joml.Vector3f;

public class Light {

    private Vector3f position;
    private Vector3f color;
    private Shader shader;
    private boolean isStatic = false;

    public Light(Vector3f position, Vector3f color, Shader shader){
        this.position = position;
        this.color = color;
        this.shader = shader;
    }

    public void render(){
        if(!isStatic) {
            shader.getUniformHandler().setUniform3f("lightPosition", position);
            shader.getUniformHandler().setUniform3f("lightColor", color);
        }
    }

    public void setStatic(){
        shader.bind();

        shader.getUniformHandler().setUniform3f("lightPosition", position);
        shader.getUniformHandler().setUniform3f("lightColor", color);

        shader.unbind();

        isStatic = true;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
}
