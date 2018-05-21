package com.chargnn.core.io;

import com.chargnn.Main;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallback;


public class Mouse extends GLFWCursorPosCallback {


    private static Vector2f position = new Vector2f().zero();
    private Vector2f oldPosition = new Vector2f().zero();

    private static float rotX = 0;
    private static float rotY = 0;


    @Override
    public void invoke(long window, double x, double y) {
        oldPosition = position;
        position.x = (float) x;
        position.y = (float) y;

        updateInput();
    }

    private void updateInput(){
        rotX = oldPosition.x - Main.getDisplay().getWindowSize()[0] / 2;
        rotY = oldPosition.y - Main.getDisplay().getWindowSize()[1] / 2;
    }

    public static Vector2f getPosition() {
        return position;
    }

    public Vector2f getOldPosition() {
        return oldPosition;
    }


    public static float getRotX() {
        return rotX;
    }

    public static float getRotY() {
        return rotY;
    }
}
