package pageObjects;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import main.Core;

public class ConfirmationPopUpPageObject extends Core
{

	@AndroidFindBy(id = "android:id/button1")
	public WebElement acceptButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/toggle_subscription")
	public WebElement subscriptionToggleButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/toggle_newsfeed")
	public WebElement newsFeedToggleButton;

	@AndroidFindBy(id = "android:id/button1")
	public WebElement okButton;

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	@iOSXCUITFindBy(accessibility="Allow")
	public WebElement allowFilePermission;

}
