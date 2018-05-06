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

    protected Vector3f position;
    protected Vector3f rotation;
    protected Vector3f scale;

    public Entity(Vector3f position, Vector3f rotation, Vector3f scale){
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void moveEntity(float dx, float dy, float dz){
        if(dx != 0) position.x += dx;
        if(dy != 0) position.y += dy;
        if(dz != 0) position.z += dz;
    }

    public void rotateEntity(float rx, float ry, float rz){
        if(rx != 0) rotation.x += rx;
        if(ry != 0) rotation.y += ry;
        if(rz != 0) rotation.z += rz;
    }

    public void scaleEntity(float sx, float sy, float sz){
        if(sx != 0) scale.x += sx;
        if(sy != 0) scale.y += sy;
        if(sz != 0) scale.z += sz;
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
