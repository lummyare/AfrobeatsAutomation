package pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.util.List;

import org.openqa.selenium.WebElement;

public class SessionObjects {
	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_setting")
	public WebElement settingButton;

	@AndroidFindBy(xpath = ".//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/llSessions']//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/toggle_sessions']")
	public WebElement mutipleSessionToggleButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_back_settings")
	public WebElement backButtonSetting;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_add")
	@iOSXCUITFindBy(accessibility="btn_navigation_addnew")
	public WebElement addButtonSessions;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/edt_name")
	@iOSXCUITFindBy(accessibility="txt_namefield")
	public WebElement sessionNameTextBox;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_open")
	@iOSXCUITFindBy(accessibility="btn_open")
	public WebElement openButtonSessions;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_name")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeCell[@name=\"list_session_cell\"]/XCUIElementTypeStaticText")
	public List<WebElement> sessionName;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_menu")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Afrobeats on the iPad\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[8]")
	public WebElement burgerMenu;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/btn_cart")
	public WebElement btnCart;
}
