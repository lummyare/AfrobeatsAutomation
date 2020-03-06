package pages;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.TapTempoObjects;

public class TapTempoButton extends Core
{

	String btnStatusBefore;
	
	TapTempoObjects TapTempoObject = new TapTempoObjects();

	public TapTempoButton(AppiumDriver<MobileElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		TapTempoObject);
	}

	public void enableTapTempoCheckBox()
	{
		try
		{
			wait(2);
			findWebElementByIDAndClick(TapTempoObject.settingButton);
			findWebElementByIDAndClick(TapTempoObject.tapTempoToggle);
			findWebElementByIDAndClick(TapTempoObject.backButtonSetting);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void multipleClickTapTempoButton()
	{
		try
		{
			btnStatusBefore = TapTempoObject.bpmValue.getText();
			if (driver.getPlatformName().equalsIgnoreCase("Android")) {
				for (int i = 0; i < 4; i++) {
					findWebElementByIDAndClick(TapTempoObject.tapTempoButton);
				} 
			}
			else
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            HashMap<String, String> tapObject = new HashMap<String, String>();
	            tapObject.put("element", ((RemoteWebElement) TapTempoObject.tapTempoButton).getId());
	            js.executeScript("mobile:doubleTap", tapObject);
	            js.executeScript("mobile:doubleTap", tapObject);
			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public boolean verifyTapTempoButton()
	{
		try
		{
			String btnStatusAfter = TapTempoObject.bpmValue.getText();
			if (btnStatusAfter.equals(btnStatusBefore))
			{
				System.out.println("Tap Tempo Button not Deactivated succesffuly. ");
				return false;
			}
			else
			{
				System.out.println("Tap Tempo Button Deactivated succesffuly. ");
				return true;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

}
