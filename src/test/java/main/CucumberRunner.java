package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import pages.ConfirmationPopUpPage;

@CucumberOptions(features = {"src/test/resources/features" }, glue = "stepdefinition", plugin = { "pretty",
                                "html:target/cucumber.html" })
public class CucumberRunner extends AbstractTestNGCucumberTests {

        InputStream inputStream;

        public static Properties config = null;
        public  String iosloopcount;
        ConfirmationPopUpPage confirmationPopUpPage;

        public static AppiumDriver driver;

        @BeforeTest(alwaysRun = true)
        /* this annotation is used to insert parameter in test */
        @Parameters("browser")
        /* this annotation is used to insert parameter in test */
        public void openBroswer(String browser) throws IOException, InterruptedException {

                try {
                        // Set system properties for HTTP client
                        System.setProperty("webdriver.http.factory", "jdk-http-client");
                        System.setProperty("selenium.manager.enabled", "false");
                        Properties prop = new Properties();
                        String propFileName = "config.properties";

                        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                        if (inputStream != null) {
                                prop.load(inputStream);
                        } else {
                                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                        }

                        
                        String androidDeviceName = prop.getProperty("ANDROID_DEVICE_NAME");
                        String androidPlatformVersion = prop.getProperty("ANDROID_PLATFORMVERSION");
                        
                        String androidAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                                        + File.separator + "resources" + File.separator + "installable" + File.separator+ prop.getProperty("ANDROID_APP_NAME");
                        String iOSDeviceName = prop.getProperty("IOS_DEVICE_NAME");
                        String iOSPlatformVersion = prop.getProperty("IOS_PLATFORMVERSION");
                        String iOSAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                                        + File.separator + "resources" + File.separator + "installable" + File.separator+ prop.getProperty("IOS_APP_NAME");
                        String udidIOS = prop.getProperty("IOS_UDID");
                        iosloopcount=prop.getProperty("IOS_Loop_Count");
                        if (browser.equalsIgnoreCase("iOS")) {
                                XCUITestOptions options = new XCUITestOptions();
                                options.setDeviceName(iOSDeviceName);
                                options.setPlatformVersion(iOSPlatformVersion);
                                options.setPlatformName("iOS");
                                options.setApp(iOSAppPath);
                                options.setUdid(udidIOS);
                                options.setAutomationName("XCUITest");
                        
                                driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                                driver.switchTo().alert().accept();
                                
                                // Set platform for Core utility
                                main.Core.setPlatformName("iOS");
                        

                        } else {
                                UiAutomator2Options options = new UiAutomator2Options();
                                options.setDeviceName(androidDeviceName);
                                options.setPlatformVersion(androidPlatformVersion);
                                options.setPlatformName("Android");
                                options.setApp(androidAppPath);
                                options.setAutomationName("UiAutomator2");
                                options.setAppPackage("com.suenare.iafrobeats.afrobeats");
                                options.setAppActivity("com.suenare.iafrobeats.afrobeats.SplashActivity");
                                options.setNewCommandTimeout(Duration.ofSeconds(300));
                                options.setAppWaitActivity("*");
                                options.setAppWaitDuration(Duration.ofSeconds(30));
                                options.setNoReset(false);
                                options.setFullReset(false);
                                driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

                                // Set platform for Core utility
                                main.Core.setPlatformName("Android");
                                
                                if (browser.equalsIgnoreCase("Android") || !browser.equalsIgnoreCase("iOS")) {
                                        confirmationPopUpPage = new ConfirmationPopUpPage(driver);
                                        confirmationPopUpPage.acceptConfirmation();
                                        confirmationPopUpPage.trunOffAFBOTPFeatures();
                                        confirmationPopUpPage.allowFilePermission();
                                }
                        }
                } catch (Exception e) {
                        System.out.println("Exception: " + e);
                } finally {
                        inputStream.close();
                }
                
                
                
                
        }

        @AfterTest
        public void closebrowser()
        
        {
                if (driver != null)
                {
                
                        driver.quit();
                }
        }
        
        
}
