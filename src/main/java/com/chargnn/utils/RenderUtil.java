package com.chargnn.utils;

import org.lwjgl.opengl.GL11;

public class RenderUtil {

    public static void clearScreen(float r, float g, float b, float a){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(r, g, b, a);
    }
}
