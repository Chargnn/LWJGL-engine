package com.chargnn.utils;

import com.chargnn.core.io.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;

public class CallbackEvents {

    private GLFWKeyCallback keyCallback;
    private GLFWWindowSizeCallback windowSizeCallback;

    public CallbackEvents(Display display){
        GLFW.glfwSetKeyCallback(display.getWindowID(), keyCallback = new Keyboard());

        GLFW.glfwSetWindowSizeCallback(display.getWindowID(), windowSizeCallback = new GLFWWindowSizeCallback(){
            @Override
            public void invoke(long window, int width, int height){

                display.setHasResized(true);
                GLFW.glfwSetWindowSizeCallback(display.getWindowID(), windowSizeCallback);
                GL11.glViewport(0, 0, width, height);
            }
        });
    }
}
