package com.appium.tests;

import com.appium.base.AppiumBase;
import com.appium.pages.CalcHomePage;
import com.appium.utility.ReportPortalLoggingUtils;
import com.appium.utility.ScreenshotUtility;
import com.appium.threadLocal.ThreadLocalDriver;
import com.appium.threadLocal.ThreadLocalLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;


public class Sample extends AppiumBase {

    private ScreenshotUtility screenshotUtility;
    private ReportPortalLoggingUtils reportPortalLoggingUtils;

    @BeforeClass
    public void initializeLogger() {
        ThreadLocalLogger.setLogger(LogManager.getLogger(getClass().getName()));
        screenshotUtility = new ScreenshotUtility();
        reportPortalLoggingUtils = new ReportPortalLoggingUtils();

    }

    @Test
    public void SampleTestNERCal() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("INSIDE OF SAMPLE TEST");

        ThreadLocalLogger.getLogger().info("Info Log - This should go to Report Portal.");
        ThreadLocalLogger.getLogger().debug("Debug Log");
        ThreadLocalLogger.getLogger().warn("Warning log");
        ThreadLocalLogger.getLogger().error("Error Log");
        ThreadLocalLogger.getLogger().fatal("Fatal Log");

        ThreadLocalLogger.getLogger().info("Driver Instance : " + ThreadLocalDriver.getTLDriver());
        File screenshot = screenshotUtility.takeScreenshotAsFile(ThreadLocalDriver.getTLDriver());

        // Attach Screenshot to Report Portal
        reportPortalLoggingUtils.log(screenshot,"Screenshot #1");

        CalcHomePage calcHomePage = new CalcHomePage();
        calcHomePage.enterAreaSQFT(2000.12);
        calcHomePage.switchRent(CalcHomePage.Rent.Gross);
        calcHomePage.switchRent(CalcHomePage.Rent.Net);

        calcHomePage.calculate();

        Thread.sleep(2000);

        File screenshot2 = screenshotUtility.takeScreenshotAsFile(ThreadLocalDriver.getTLDriver());

        // Attach Screenshot to Report Portal
        reportPortalLoggingUtils.log(screenshot2,"Screenshot #3");

        Assert.assertEquals("Test","Test");

    }
}
