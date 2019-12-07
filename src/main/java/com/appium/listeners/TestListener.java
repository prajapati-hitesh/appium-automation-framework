package com.appium.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        System.out.println("New Test Started" + result.getName());
    }
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Successfully Finished" + result.getName());
    }
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed" + result.getName());
    }
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped" + result.getName());
    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within success percentage" + result.getName());
    }
    public void onStart(ITestContext context) {
        System.out.println("This is onStart method" + context.getOutputDirectory());
    }
    public void onFinish(ITestContext context) {
        System.out.println("This is onFinish method" + context.getPassedTests());
        System.out.println("This is onFinish method" + context.getFailedTests());
    }
}
