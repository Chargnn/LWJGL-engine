package com.chargnn.utils;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil {

    public static void clearScreen(float r, float g, float b, float a){
        glEnable(GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(r, g, b, a);
    }
}
