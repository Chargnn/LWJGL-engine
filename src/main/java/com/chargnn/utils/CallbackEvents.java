package com.chargnn.utils;

import com.chargnn.core.io.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class CallbackEvents {

    private GLFWKeyCallback keyCallback;

    public CallbackEvents(long windowID){
        GLFW.glfwSetKeyCallback(windowID, keyCallback = new Keyboard());
    }
}
