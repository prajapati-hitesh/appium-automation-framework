package com.appium.utility;

public class WaitUtility {
    public void hardWait(int timeInSeconds) throws InterruptedException {
        Thread.sleep(timeInSeconds * 1000);
    }
}
