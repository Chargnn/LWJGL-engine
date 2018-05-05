package com.chargnn.utils;

import com.chargnn.entityObject.Entity;
import org.joml.Matrix4f;

public class Mathf {

    public static Matrix4f createTransformationMatrix(Entity entity){
        Matrix4f matrix = new Matrix4f().identity();

        matrix.translate(entity.getPosition());
        matrix.rotateXYZ(entity.getRotation());
        matrix.scale(entity.getScale());

        return matrix;
    }

}
