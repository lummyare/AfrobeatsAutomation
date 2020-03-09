package pageObjects;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import main.Core;

public class MainScreenPageObject extends Core
{

	@AndroidFindBy(id = "android:id/content")
	public WebElement mainActivityFrame;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/mainLayout")
	public WebElement sliderActivityFrame;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tvLibraryName")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Afrobeats on the iPad\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]")
	public WebElement mainPageTitle;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tvtapTempoPressed")
	public WebElement tapTempoButton;

}
