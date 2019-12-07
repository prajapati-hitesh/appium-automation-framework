package com.appium.utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotUtility {

    /**
     * Take screenshot as Base64 format
     * @param _driver AppiumDriver object
     * @return String in BASE64 Format
     */
    public String takeScreenshotAsBase64(AppiumDriver<?> _driver) {
        return ((TakesScreenshot) _driver).getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Takes Screenshot of an element as Base64 String
     * @param _driver AppiumDriver object
     * @param element MobileElement object
     * @return Base64 String
     * @throws IOException
     */
    public String takeScreenshotAsBase64(AppiumDriver<?> _driver, MobileElement element) throws IOException {
        return convertToBase64Image(getScreenshotOfElement(_driver,element));
    }

    /**
     * Takes Screenshot of an element as File type
     * @param _driver AppiumDriver object
     * @param element MobileElement object
     * @return Screenshot as file
     * @throws IOException
     */
    public String takeScreenshotAsFile(AppiumDriver<?> _driver, MobileElement element) throws IOException {
        return convertToBase64Image(getScreenshotOfElement(_driver,element));
    }

    /**
     * Take screenshot at File
     * @param _driver
     * @return
     */
    public File takeScreenshotAsFile(AppiumDriver<?> _driver) {
        return ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
    }


    /**
     * Converts the image of type File to Base64 String
     * @param imageAsFileObj Image object of type File
     * @return Base64 String
     */
    private String convertToBase64Image(File imageAsFileObj) {
        String base64Image = "";

        try (FileInputStream imageInFile = new FileInputStream(imageAsFileObj)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) imageAsFileObj.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    /**
     * Takes the screenshot of element
     * @param _driver AppiumDriver object
     * @param element MobileElement Object
     * @return Screenshot object of type File
     * @throws IOException
     */
    private File getScreenshotOfElement(AppiumDriver<?> _driver, MobileElement element) throws IOException {

        // Get the dimensions of the element
        int eleWidth = element.getSize().getWidth();
        int eleHeight = element.getSize().getHeight();

        // Take Screenshot
        File screenShotAsFile = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullScreenshot = ImageIO.read(screenShotAsFile);

        // Get the location of element on the page
        Point point = element.getLocation();

        // Crop the entire page screenshot to get only the element screenshot
        BufferedImage elementScreenshot = fullScreenshot.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);

        ImageIO.write(elementScreenshot,"png",screenShotAsFile);

        return screenShotAsFile;
    }
}
