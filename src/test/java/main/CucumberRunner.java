package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByWindowsAutomation;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import pages.ConfirmationPopUpPage;

@CucumberOptions(strict = true, monochrome = true, features = {"src/test/resources/features" }, glue = "stepdefinition", format = { "pretty",
				"html:target/cucumber.html" })
public class CucumberRunner extends AbstractTestNGCucumberTests {

	InputStream inputStream;

	public static Properties config = null;
	ConfirmationPopUpPage confirmationPopUpPage;

	public static AppiumDriver<MobileElement> driver;

	@BeforeTest(alwaysRun = true)
	/* this annotation is used to insert parameter in test */
	@Parameters("browser")
	/* this annotation is used to insert parameter in test */
	public void openBroswer(String browser) throws IOException, InterruptedException {

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// get the property value and print it out
			String androidDeviceName = prop.getProperty("ANDROID_DEVICE_NAME");
			String androidPlatformVersion = prop.getProperty("ANDROID_PLATFORMVERSION");
			// String platformName = prop.getProperty("platformName");
			String androidAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "installable" + File.separator+ prop.getProperty("ANDROID_APP_NAME");
			String iOSDeviceName = prop.getProperty("IOS_DEVICE_NAME");
			String iOSPlatformVersion = prop.getProperty("IOS_PLATFORMVERSION");
			String iOSAppPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "installable" + File.separator+ prop.getProperty("IOS_APP_NAME");
			String udidIOS = prop.getProperty("IOS_UDID");
			if (browser.equalsIgnoreCase("iOS")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", iOSDeviceName);
				capabilities.setCapability("platformVersion", iOSPlatformVersion);
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("app", iOSAppPath);
				capabilities.setCapability("udid", udidIOS);
				capabilities.setCapability("automationName", "XCUITest");
				// capabilities.setCapability("autoAcceptAlerts", true);
				driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.switchTo().alert().accept();
				// wait(5);

			} else {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", androidDeviceName);
				capabilities.setCapability("platformVersion", androidPlatformVersion);
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("app", androidAppPath); //
				driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

				// always run before Test Start confirmationPopUpPageObject test =
				if (driver.getPlatformName().equalsIgnoreCase("Android")) {
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

}
