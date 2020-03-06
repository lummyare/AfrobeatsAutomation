package pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.WebElement;

public class NormButtonObjects {

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_vol_norm")
	@iOSXCUITFindBy(accessibility="VOL NORM")
	public WebElement volNormButton;
}
