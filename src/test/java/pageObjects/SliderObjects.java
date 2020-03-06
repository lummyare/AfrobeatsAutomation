package pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.WebElement;

public class SliderObjects {

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_bmp_counter")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Afrobeats on the iPad\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]")//XCUIElementTypeButton[@name="150"]
	public WebElement bpmValue;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tvCurrentBPM")
	@iOSXCUITFindBy(accessibility="lbl_bpm")
	public WebElement bpmSliderMenuValue;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/sbBPM")
	@iOSXCUITFindBy(accessibility="slider_bpm")
	public WebElement bpmSliderValue;
	
	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/btnBPm_Plus")
	@iOSXCUITFindBy(accessibility="btn_bpm_plus")
	public WebElement sliderPlusButton;
	
	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/btnBPm_Minu")
	@iOSXCUITFindBy(accessibility="btn_bpm_minus")
	public WebElement sliderMinusButton;
	

}
