package com.appium.framework.helper;

import com.appium.framework.utility.PropertiesUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AppiumHelper {
    protected AppiumDriver<MobileElement> driver;
    protected WebDriverWait wait;


    public AppiumHelper(AppiumDriver<MobileElement> _driver) {
        this.driver = _driver;
        wait = new WebDriverWait(_driver, 30);
    }

    public By findBy(PropertiesUtility propUtilObj, String locatorKey) {
        try {
            String locatedBy = propUtilObj.getLocatorType(locatorKey);
            String locatedValue = propUtilObj.getLocatorValue(locatorKey);

            if(locatedBy != null && locatedValue != null) {
                switch (locatedBy.toUpperCase()) {
                    case "ACCESSIBILITYID":
                        return MobileBy.ByAccessibilityId.AccessibilityId(locatedValue);
                    case "CLASSNAME":
                        return MobileBy.ByClassName.className(locatedValue);
                    case "ID":
                        return MobileBy.ById.id(locatedValue);
                    case "NAME":
                        return MobileBy.ByName.name(locatedValue);
                    case "XPATH":
                        return MobileBy.ByXPath.xpath(locatedValue);
                    case "LINKTEXT":
                        return MobileBy.ByLinkText.linkText(locatedValue);
                    case "PARTIALLINKTEXT":
                        return MobileBy.ByPartialLinkText.partialLinkText(locatedValue);
                }
            }

        } catch (NoSuchElementException ex) {
            System.out.println("Exception at <AppiumHelper>.<findBy> for Locator Key : " + locatorKey);
        }
        return null;
    }

    public MobileElement findElementBy(PropertiesUtility propUtilObj, String locatorKey) {
        try {
            String locatedBy = propUtilObj.getLocatorType(locatorKey);
            String locatedValue = propUtilObj.getLocatorValue(locatorKey);

            if(locatedBy != null && locatedValue != null) {
                switch (locatedBy.toUpperCase()) {
                    case "ACCESSIBILITYID":
                        return driver.findElementByAccessibilityId(locatedValue);
                    case "CLASSNAME":
                        return driver.findElementByClassName(locatedValue);
                    case "ID":
                        return driver.findElementById(locatedValue);
                    case "NAME":
                        return driver.findElementByName(locatedValue);
                    case "XPATH":
                        return driver.findElementByXPath(locatedValue);
                    case "IMAGE":
                        return driver.findElementByImage(locatedValue);
                    case "LINKTEXT":
                        return driver.findElementByLinkText(locatedValue);
                    case "PARTIALLINKTEXT":
                        return driver.findElementByPartialLinkText(locatedValue);
                }
            }

        } catch (NoSuchElementException ex) {
            System.out.println("Exception at <AppiumHelper>.<findElementBy> for Locator Key : " + locatorKey);
        }
        return null;
    }

    public List<MobileElement> findElementsBy(PropertiesUtility propUtilObj, String locatorKey) {
        try {
            String locatedBy = propUtilObj.getLocatorType(locatorKey);
            String locatedValue = propUtilObj.getLocatorValue(locatorKey);

            if(locatedBy != null && locatedValue != null) {
                switch (locatedBy.toUpperCase()) {
                    case "ACCESSIBILITYID":
                        return driver.findElementsByAccessibilityId(locatedValue);
                    case "CLASSNAME":
                        return driver.findElementsByClassName(locatedValue);
                    case "ID":
                        return driver.findElementsById(locatedValue);
                    case "NAME":
                        return driver.findElementsByName(locatedValue);
                    case "XPATH":
                        return driver.findElementsByXPath(locatedValue);
                    case "IMAGE":
                        return driver.findElementsByImage(locatedValue);
                    case "LINKTEXT":
                        return driver.findElementsByLinkText(locatedValue);
                    case "PARTIALLINKTEXT":
                        return driver.findElementsByPartialLinkText(locatedValue);
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Exception at <AppiumHelper>.<findElementsBy> for Locator Key : " + locatorKey);
        }
        return null;
    }

    public boolean isElementPresent(MobileBy by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void sleep (long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public String getText(MobileElement element) {
        return element.getText();
    }

    public String getAtrribute(MobileElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public boolean isDisplayed(MobileElement element) {
        return element.isDisplayed();
    }

    public boolean isEnabled(MobileElement element) {
        return element.isEnabled();
    }

}
