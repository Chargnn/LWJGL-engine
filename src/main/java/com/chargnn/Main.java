package com.chargnn;

import com.chargnn.core.GameCore;
import com.chargnn.utils.Display;
import com.chargnn.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main{
    private long lastFrame;
    private int fps;
    private long lastFPS;

    private static Display display;
    private GameCore core;

    private Main(){
        display = new Display("Engine test", 500, 500);
    }

    public static void main(String[] args){
        Main main = new Main();

        main.initGL();
    }

    private void initGL(){
        Time.getDelta(lastFrame);
        lastFrame = Time.getTime();
        lastFPS = Time.getTime();

        GL.createCapabilities();

        GL11.glViewport(0, 0, display.getWindowSize()[0], display.getWindowSize()[1]);

        core = new GameCore();
        core.init();

        run();
    }

    public void run(){
        while(!display.isCloseRequested()){
            int delta = Time.getDelta(lastFrame);
            lastFrame = Time.getTime();

            update(delta);
            render();
        }

        stop();
    }

    private void update(int delta){
        core.update(delta);
        GLFW.glfwPollEvents();

        updateFPS();
    }

    private void render(){
        core.render();
        GLFW.glfwSwapBuffers(display.getWindowID());
    }

    private void stop(){
        GLFW.glfwTerminate();
    }

    public void updateFPS() {
        if (Time.getTime() - lastFPS > 1000) {
            System.out.println("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public static Display getDisplay(){
        return display;
    }
}
