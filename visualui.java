package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;

public class Visualui {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        RemoteWebDriver driver = null;


        Hashtable<String, Integer> errorColor= new Hashtable<>();
        errorColor.put("red",255);
        errorColor.put("green",0);
        errorColor.put("blue",0);

        HashMap<String,Object> output= new HashMap<String, Object>();
        output.put("errorColor",errorColor);//Output Difference error color
        output.put("transparency",0.1);// Set transparency of Output
        output.put("largeImageThreshold",1200);// the granularity to which the comparison happens(the scale or level of detail in a set of data.)Range-100-1200


        HashMap<String, Object> sm=new HashMap<String, Object>();
        sm.put("output",output);
        sm.put("scaleToSameSize",true);//scale to same size, when baseline image and comparision image is of different size, use true





        String username = "your_lt_username";
        String access_key = "your_lt_access_key";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "VisualUI");
        capabilities.setCapability("name", "Visual Ui Testing");
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("platformName", "Windows 11");
        capabilities.setCapability("browserVersion", "105.0");
        capabilities.setCapability("visual",true);
        capabilities.setCapability("smartUI.project","20th feb");
        //capabilities.setCapability("smartUI.build","10689a4");
        capabilities.setCapability("smartUI.options",sm);
        //multiple baseline images for your project
        capabilities.setCapability("smartUI.build","chrome 1");
        capabilities.setCapability("selenium_version", "4.0.0");

        driver = new RemoteWebDriver(new URL("http://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);




        driver.get("https://www.lambdatest.com/");
        Thread.sleep(5000);

        driver.executeScript("smartui.takeFullPageScreenshot=pic1");
        Thread.sleep(1000);

       driver.get("https://www.lambdatest.com/pricing");
        Thread.sleep(5000);

        driver.executeScript("smartui.takeScreenshot=pic2");
        Thread.sleep(1000);

        driver.get("https://www.lambdatest.com/support/docs/");
        Thread.sleep(5000);
        driver.executeScript("smartui.takeScreenshot=pic3");
        Thread.sleep(1000);



        driver.executeScript("lambda-status=passed");
        driver.quit();



    }
}
