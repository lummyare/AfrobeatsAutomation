package pageObjects;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class TapTempoObjects
{

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tvtapTempoPressed")
	@iOSXCUITFindBy(accessibility="TAP TEMPO")
	public WebElement tapTempoButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_setting")
	@iOSXCUITFindBy(accessibility="btn_navigation_settings")
	public WebElement settingButton;//XCUIElementTypeButton[@name=""]

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/toggle_tap_tempo")
	public WebElement tapTempoToggle;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_back_settings")
	public WebElement backButtonSetting;
	
	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_bmp_counter")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Afrobeats on the iPad\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]")//XCUIElementTypeButton[@name="150"]
	public WebElement bpmValue;
}
