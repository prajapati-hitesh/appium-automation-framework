package com.appium.framework.pages;

import com.appium.framework.helper.AppiumInteractions;
import com.appium.framework.threadLocal.ThreadLocalDriver;
import com.appium.framework.threadLocal.ThreadLocalLogger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CalcHomePage {


    @AndroidFindBy(accessibility = "txtAreaSF")
    @iOSXCUITFindBy(accessibility = "txtAreaSF")
    private MobileElement areaSFTxtElement;

    @AndroidFindBy(accessibility = "dtLeaseStart")
    @iOSXCUITFindBy(accessibility = "dtLeaseStart")
    private MobileElement areaSFValidationLblElement;

    @AndroidFindBy(accessibility = "switchRentType")
    @iOSXCUITFindBy(accessibility = "switchRentType")
    private MobileElement rentSwitchElement;

    @AndroidFindBy(accessibility = "dtLeaseStart")
    @iOSXCUITFindBy(accessibility = "dtLeaseStart")
    MobileElement leaseStartDateElement;

   /* @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement leaseStartValidationLblElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement leaseTermTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement leaseEndDateReadOnlyDateElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement baseRentNumberOfMonthsTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement baseRentValidationOfMonthLblElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement baseRentValidationLblElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement baseRentPSFTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement baseRentCollapseBtnElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement baseRentAddBtnElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement additionalRentPSFTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement additionalRentYearSelectionElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement additionalRentPercentageGrowthTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement tiAllowancePSFTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement tiAllowancePaymentMonthTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement netFreeRentSwitchElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement rentFreeNumberOfMonthsTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement leasingCommisionPSFTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement discountRatePercentageTxtElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement resetBtnElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement settingsBtnElement;

    @AndroidFindBy(accessibility = "")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement homeBtnElement;*/

    @AndroidFindBy(accessibility = "Calculate")
    @iOSXCUITFindBy(accessibility = "Calculate")
    private MobileElement calculateBtnElement;

    @iOSXCUITFindBy(className = "XCUIElementTypePickerWheel")
    private List<MobileElement> datePickerWheelElements;

    public CalcHomePage() {
        ThreadLocalLogger.setLogger(LogManager.getLogger(CalcHomePage.class.getName()));
        PageFactory.initElements(new AppiumFieldDecorator(ThreadLocalDriver.getTLDriver()), this);
    }

    public void selectDate(AppiumInteractions.Calender month, int date, int year) throws InterruptedException {
        // click on lease start
        leaseStartDateElement.click();
        Thread.sleep(2000);

        // select Date
        datePickerWheelElements.get(1).sendKeys(String.valueOf(date));

    }

    public void enterAreaSQFT(double areaInSqFt) {
        areaSFTxtElement.clear();
        areaSFTxtElement.sendKeys(String.valueOf(areaInSqFt));
    }

    public void switchRent(Rent rentType) {
        if(rentType.toString().toUpperCase().equals("GROSS")) {
            if(rentSwitchElement.getAttribute("value").equals("1")) {
                rentSwitchElement.click();
            }
        } else if(rentType.toString().toUpperCase().equals("NET")) {
            if(rentSwitchElement.getAttribute("value").equals("0")) {
                rentSwitchElement.click();
            }
        }
    }

    public void calculate() {
        calculateBtnElement.click();
    }

    public enum Rent {
        Gross,
        Net
    }
}
