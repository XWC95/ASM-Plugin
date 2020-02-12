package com.vea.ams.lib.time;

import java.util.concurrent.TimeUnit;

public class TimeUtil implements Runnable {
    private static volatile long currentTimeMillis;

    static {
        currentTimeMillis = System.currentTimeMillis(); // fetch system time for init
        Thread deamon = new Thread(new TimeUtil());
        deamon.setDaemon(true);
        deamon.setName("time tick thread");
        deamon.start();
    }

    public void run() {
        while (true) {
            currentTimeMillis = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                //unreachable
            }
        }
    }

    public static long currentTimeMillis() {
        return currentTimeMillis;
    }

}