package com.chargnn;

import com.chargnn.core.GameCore;
import com.chargnn.utils.Display;
import com.chargnn.utils.readers.SettingsManager;
import com.chargnn.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main{
    private long lastFrame;
    private int fps;
    private long lastFPS;

    private static Display display;
    private GameCore core;

    private Main() throws IOException, ParserConfigurationException, SAXException {
        display = new Display(SettingsManager.getElementsByTagName("Title"), SettingsManager.getElementsByTagNameI("Width"), SettingsManager.getElementsByTagNameI("Height"));
        display.setVSync(SettingsManager.getElementsByTagNameBool("Vsync"));
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Main main = new Main();

        main.initGL();
    }

    private void initGL() throws IOException, SAXException, ParserConfigurationException {
        Time.getDelta(lastFrame);
        lastFrame = Time.getTime();
        lastFPS = Time.getTime();

        GL.createCapabilities();

        core = new GameCore();
        core.init();

        run();
    }

    public void run() throws IOException, SAXException, ParserConfigurationException {
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

    private void render() throws ParserConfigurationException, SAXException, IOException {
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
