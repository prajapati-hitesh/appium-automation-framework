package com.appium.framework.helper;

import com.appium.framework.threadLocal.ThreadLocalDriver;
import com.appium.framework.utility.DateUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppiumInteractions {

    private AppiumDriver<MobileElement> driver;
    private Actions mobileAction;
    private TouchActions touchActions;

    public AppiumInteractions(AppiumDriver<MobileElement> _driver) {
        this.driver = _driver;
        this.mobileAction = new Actions(_driver);
        this.touchActions = new TouchActions(_driver);
    }

    public void moveToElement(MobileElement elementObj) {
        mobileAction.moveToElement(elementObj)
                .build()
                .perform();
    }

    public void click(MobileElement element) {
        mobileAction.moveToElement(element)
                .click(element)
                .build()
                .perform();
    }

    public void doubleClick(MobileElement element) {
        mobileAction.moveToElement(element)
                .doubleClick(element)
                .build()
                .perform();
    }

    public void clickAndHold(MobileElement element) {
        mobileAction.moveToElement(element)
                .clickAndHold(element)
                .build()
                .perform();
    }

    public void selectDateFromPickerWheel(List<RemoteWebElement> element, Calender month, int date, int year) {
        DateUtility dateUtility = new DateUtility();

        Calender calArray[] = Calender.values();
        int monthValueInNumber = 0;

        for (Calender c :calArray) {
            if(c.equals(month)) {
                monthValueInNumber = c.ordinal();
                break;
            }
        }

        int indexOfMonth = monthValueInNumber + 1;

        JavascriptExecutor js = (JavascriptExecutor) ThreadLocalDriver.getTLDriver();
        // Automate Month Wheel
        Map<String, Object> monthMap = new HashMap<>();

        if(indexOfMonth <= dateUtility.getCurrentMonthAsNumber()) {
            monthMap.put("order","previous");
        } else if(indexOfMonth >= dateUtility.getCurrentMonthAsNumber()) {
            monthMap.put("order","next");
        }

        monthMap.put("offset", 0.15);
        monthMap.put("element", ((RemoteWebElement) element.get(0)).getId());
        js.executeScript("mobile: selectPickerWheelValue", monthMap);

        // Automate Date Wheel
        Map<String, Object> dateMap = new HashMap<>();

        if(date <= dateUtility.getCurrentDate()) {
            dateMap.put("order","previous");
        } else if(date >= dateUtility.getCurrentDate()) {
            dateMap.put("order","next");
        }

        dateMap.put("offset", 0.15);
        dateMap.put("element", ((RemoteWebElement) element.get(0)).getId());
        js.executeScript("mobile: selectPickerWheelValue", dateMap);

        // Automate Year Wheel
        Map<String, Object> yearMap = new HashMap<>();

        if(year <= dateUtility.getCurrentYear()) {
            yearMap.put("order","previous");
        } else if(year >= dateUtility.getCurrentYear()) {
            yearMap.put("order","next");
        }

        yearMap.put("offset", 0.15);
        yearMap.put("element", ((RemoteWebElement) element.get(0)).getId());
        js.executeScript("mobile: selectPickerWheelValue", yearMap);
    }

    public enum Calender {
        JANUARY, FEBRUARY, MARCH, APRIL,
        MAY, JUNE, JULY, AUGUST, SEPTEMBER,
        OCTOBER, NOVEMBER, DECEMBER
    }
}
