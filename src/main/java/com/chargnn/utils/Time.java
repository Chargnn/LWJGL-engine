package com.chargnn.utils;

public class Time {

    public static long getTime(){
        return System.nanoTime() / 1000000;
    }

    public static int getDelta(long lastFrame) {
        long time = getTime();
        int delta = (int) (time - lastFrame);

        return delta;
    }

}
