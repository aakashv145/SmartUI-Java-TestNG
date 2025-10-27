package com.lambdatest.sdk;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.lambdatest.SmartUISnapshot;

public class SmartUISDKCloud {

    private RemoteWebDriver driver;
    private String Status = "failed";
    private String githubURL = System.getenv("GITHUB_URL");
   
    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = "zeeshans";
        String authkey = "VwbQQouOr96dC0EI7uzHXIiXPzsn167YntkH0kOKdyuHttQlNi";
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 11");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "141");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("smartUI.project", "veeva_repeat_logs");
        caps.setCapability("smartUI.baseline", false);        
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        
        if (githubURL != null) {
            Map<String, String> github = new HashMap<String, String>();
            github.put("url",githubURL);
            caps.setCapability("github", github);
        }
        System.out.println(caps);
        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    @Test
    public void basicTest() throws Exception {
        System.out.println("Loading Url");
        driver.get("https://www.lambdatest.com/visual-regression-testing");
        Thread.sleep(1000);  
        SmartUISnapshot.smartuiSnapshot(driver, "visual-regression-testing");
        Thread.sleep(5000);
        driver.get("https://www.lambdatest.com");
        Thread.sleep(1000);
        SmartUISnapshot.smartuiSnapshot(driver, "homepage");
        Thread.sleep(1000);
        System.out.println("Test Finished");
    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}
