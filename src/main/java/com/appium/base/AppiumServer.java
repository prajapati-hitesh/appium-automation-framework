package com.appium.base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
    private AppiumDriverLocalService appiumDriverLocalService;
    private AppiumServiceBuilder appiumServiceBuilder;

    /***
     *
     * @param atIPAddress IP Address At Which you want to start
     *                    Appium Server
     * @return AppiumDriverLocalService
     */
    public AppiumDriverLocalService buildAppiumServerProcess(String atIPAddress) {
        try {
            appiumServiceBuilder = new AppiumServiceBuilder();
            appiumServiceBuilder.withIPAddress(atIPAddress);
            appiumServiceBuilder.usingAnyFreePort();

            appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return appiumDriverLocalService;
    }
}
