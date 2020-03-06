package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.ConfirmationPopUpPageObject;

public class ConfirmationPopUpPage extends Core
{

	ConfirmationPopUpPageObject confirmationPopUpPageObject = new ConfirmationPopUpPageObject();

	public ConfirmationPopUpPage(AppiumDriver<MobileElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		confirmationPopUpPageObject);
	}

	public void acceptConfirmation()
	{
		findWebElementByIDAndClick(confirmationPopUpPageObject.acceptButton);
	}

	public void allowFilePermission()
	{
		if (checkElementDisplayed(confirmationPopUpPageObject.allowFilePermission))
			findWebElementByIDAndClick(confirmationPopUpPageObject.allowFilePermission);
	}

	public void trunOffAFBOTPFeatures()
	{
		findWebElementByIDAndClick(confirmationPopUpPageObject.subscriptionToggleButton);
		findWebElementByIDAndClick(confirmationPopUpPageObject.newsFeedToggleButton);
		findWebElementByIDAndClick(confirmationPopUpPageObject.okButton);

	}

}
