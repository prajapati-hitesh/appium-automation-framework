package com.appium.framework.base;

import com.appium.framework.threadLocal.ThreadAppiumServer;
import com.appium.framework.threadLocal.ThreadLocalDriver;
import com.appium.framework.utility.DateUtility;
import com.appium.framework.utility.SystemUtility;
import com.appium.framework.utility.WaitUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumBase {

    public AppiumDriver<MobileElement> driver;
    protected DesiredCapabilities caps = new DesiredCapabilities();
    WaitUtility waitUtility = new WaitUtility();

    private static final String USER_DIR = new SystemUtility().getUserDirectory();
    private static final String FILE_SEPARATOR = new SystemUtility().getFileSeparator();


    @BeforeTest(alwaysRun = true)
    @Parameters({"device-name","platform-name","platform-version",
                "server-url","udid","app-package","app-activity","bundle-id","automation-name","wda-port","wda-system-port"})
    public void setUpAppium(String _deviceName, String _platformName, String _platformVersion,
                            @Optional String _serverUrl,
                            @Optional("Optional UDID") String _udid,
                            @Optional("Optional appPackage") String _appPackage,
                            @Optional("Optional appActivity") String _appActivity,
                            @Optional("Optional BundleID") String _bundleID,
                            @Optional("Optional AutomationName") String _automationName,
                            @Optional("Optional iOS WDA Port") String _wdaPort,
                            @Optional("Optional Android System Port") String _wdaSysPort) {
        try {
            // Set File Separator based on Current Operating System. i.e Windows or Unix or Mac
            System.setProperty("fileSeparator", FILE_SEPARATOR);

            // Set System Property of Log4j2 Logger's file name to Device's name
            System.setProperty("logFileName","Automation-script-log - " + new DateUtility().getCurrentTimeStamp());

            // ThreadContext.push("DEVICE_NAME",_deviceName);
            if(_platformName.trim().toUpperCase().equals("ANDROID")) {

                // Unlock the device if it is locked.
                final Runtime rt = Runtime.getRuntime();
                rt.exec("adb shell input keyevent 224");

                // Build Appium Service
                ThreadAppiumServer.setAppiumServerDriverService(new AppiumServer().buildAppiumServerProcess("127.0.0.1"));

                // Start Appium Service
                ThreadAppiumServer.getAppiumServerDriverService().start();

                // getAppiumServerUrl
                String appiumServerUrlForAndroid = ThreadAppiumServer.getAppiumServerDriverService().getUrl().toString();

                System.out.println("Device Name : " + _deviceName + " has Appium Server URL As : " + appiumServerUrlForAndroid);

                waitUtility.hardWait(5);

                androidDeviceSetup(_deviceName,_platformName,_platformVersion,_automationName,appiumServerUrlForAndroid,_udid,_appPackage,_appActivity,_wdaSysPort);

                ThreadContext.put("DEVICE_NAME", _deviceName);
            }
            else if(_platformName.trim().toUpperCase().equals("IOS")) {


                // Build Appium Service
                ThreadAppiumServer.setAppiumServerDriverService(new AppiumServer().buildAppiumServerProcess("127.0.0.1"));

                // Start Appium Service
                ThreadAppiumServer.getAppiumServerDriverService().start();

                String appiumServerUrlForiOS = ThreadAppiumServer.getAppiumServerDriverService().getUrl().toString();

                System.out.println("Device Name : " + _deviceName + " has Appium Server URL As : " + appiumServerUrlForiOS);

                waitUtility.hardWait(5);

                iOsDeviceSetup(_deviceName,_platformName,_platformVersion,_automationName, appiumServerUrlForiOS, _udid, _bundleID,_wdaPort);
                ThreadContext.put("DEVICE_NAME", _deviceName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void androidDeviceSetup(String aDeviceName, String aPlatformName, String aPlatformVersion,
                                    String aAutomationName,String aServerUrl, String aUDID, String aAppPackage, String aAppActivity, String aWdaSysPort) {
        try {
            caps.setCapability("platformName",aPlatformName);
            caps.setCapability("platformVersion", aPlatformVersion);
            caps.setCapability("deviceName", aDeviceName);
            caps.setCapability("automationName", aAutomationName);
            caps.setCapability("appPackage",aAppPackage);
            caps.setCapability("appActivity",aAppActivity);
            caps.setCapability("systemPort", aWdaSysPort);
            // caps.setCapability("udid", aUDID);

            // Start Android Driver instance
            ThreadLocalDriver.setTLDriver(new AndroidDriver<MobileElement>(new URL(aServerUrl),caps));
            ThreadLocalDriver.getTLDriver().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            ThreadLocalDriver.getTLDriver().manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
            ThreadLocalDriver.getTLDriver().manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void iOsDeviceSetup(String iDeviceName, String iPlatformName, String iPlatformVersion, String iAutomationName,
                                String iServerUrl, String iUDID, String iBundleID, String iWdaPort) {
        try {
            caps.setCapability("platformName",iPlatformName);
            caps.setCapability("platformVersion", iPlatformVersion);
            caps.setCapability("deviceName", iDeviceName);
            caps.setCapability("udid", iUDID);
            caps.setCapability("automationName", iAutomationName);
            caps.setCapability("bundleId", iBundleID);
            caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, iWdaPort);


            // Start IOS Driver Instance
            ThreadLocalDriver.setTLDriver(new IOSDriver<MobileElement>(new URL(iServerUrl), caps));;
            ThreadLocalDriver.getTLDriver().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            ThreadLocalDriver.getTLDriver().manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
            ThreadLocalDriver.getTLDriver().manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeAppiumServerLogsToFile() {
        try {
            SystemUtility systemUtility = new SystemUtility();
            String fileSeparator = systemUtility.getFileSeparator();
            String deviceName = null;

            if(ThreadLocalDriver.getTLDriver() instanceof AndroidDriver<?>) {
                deviceName = ThreadLocalDriver.getTLDriver().getCapabilities().getCapability("deviceModel").toString();
            }
            else if(ThreadLocalDriver.getTLDriver() instanceof IOSDriver<?>) {
                deviceName = ThreadLocalDriver.getTLDriver().getCapabilities().getCapability("deviceName").toString();
            }


            String filePath = USER_DIR
                    + fileSeparator + "appium-server-logs"
                    + fileSeparator + deviceName
                    + " (v" + ThreadLocalDriver.getTLDriver().getCapabilities().getCapability("platformVersion") + ") - "
                    + new DateUtility().getCurrentTimeStamp() + ".log";



            File appiumLogFile = new File(filePath);
            if(!appiumLogFile.getParentFile().exists()) {
                appiumLogFile.getParentFile().mkdirs();
            }

            // Write appium server logs to file.
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(ThreadAppiumServer.getAppiumServerDriverService().getStdOut().getBytes());
            fileOutputStream.flush();

            // Close file input stream
            fileOutputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            // Write appium server logs to file.
            writeAppiumServerLogsToFile();

            if(ThreadLocalDriver.getTLDriver() != null) {
                ThreadLocalDriver.getTLDriver().quit();
            }
            // Close appium server
            ThreadAppiumServer.getAppiumServerDriverService().stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
