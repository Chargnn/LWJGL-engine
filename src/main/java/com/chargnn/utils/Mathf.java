package com.chargnn.utils;

import com.chargnn.entityObject.Entity;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Mathf {

    public static Matrix4f createTransformationMatrix(Entity entity){
        Matrix4f matrix = new Matrix4f().identity();

        matrix.translate(entity.getPosition());
        matrix.rotateXYZ(entity.getRotation());
        matrix.scale(entity.getScale());

        return matrix;
    }

    public static Matrix4f createProjectionMatrix(Entity entity, Display display){
        final float FOV = 70.0f;
        final float NEAR= 0.001f;
        final float FAR = 1000.0f;

        float aspectRatio = (float) display.getWindowSize()[0] / (float) display.getWindowSize()[1];
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2.0f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR - NEAR;

        Matrix4f matrix = new Matrix4f().identity();
        matrix.m00(x_scale);
        matrix.m11(y_scale);
        matrix.m22(-((FAR + NEAR) / frustum_length));
        matrix.m23(-1);
        matrix.m32(-((2 * NEAR + FAR) / frustum_length));
        matrix.m33(0);

        return matrix;
    }

    public static Matrix4f createViewMatrix(Entity entity) {
        Matrix4f matrix = new Matrix4f().identity();
        Vector3f cameraPos = entity.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);

        matrix.rotateXYZ(entity.getRotation());
        matrix.translate(negativeCameraPos);
        return matrix;
    }

}
