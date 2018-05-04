package com.chargnn;

import com.chargnn.Utils.Display;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main{
    private Display display;

    private Main(){
        display = new Display("Engine test", 500, 500);
    }

    public static void main(String[] args){
        Main main = new Main();

        main.initGL();
    }

    private void initGL(){
        GL.createCapabilities();

        GL11.glViewport(0, 0, display.getWindowSize()[0], display.getWindowSize()[1]);

        run();
    }

    public void run(){
        while(!display.isCloseRequested()){
            render();
            update();
        }

        stop();
    }

    private void update(){
        GLFW.glfwPollEvents();
    }

    private void render(){
        GLFW.glfwSwapBuffers(display.getWindowID());
    }

    private void stop(){
        GLFW.glfwTerminate();
    }

}
