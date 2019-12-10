package com.appium.framework.threadLocal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ThreadLocalDriver {
    private static ThreadLocalDriver instance = null;
    private static ThreadLocal<AppiumDriver> tlDriver = new ThreadLocal<AppiumDriver>();

    public static AppiumDriver<MobileElement> getTLDriver() {
        return tlDriver.get();
    }

    public static void setTLDriver(AppiumDriver<?> driver) {
        tlDriver.set(driver);
    }
}
