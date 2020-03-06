package pageObjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.util.List;

import org.openqa.selenium.WebElement;

public class MusicPadObjects {

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/rlRow")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeCollectionView//XCUIElementTypeStaticText")
	public List<WebElement> musicPadIcon;

	@AndroidFindBy(xpath = ".//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/rlRow']//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/img_music']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeCollectionView//XCUIElementTypeStaticText")
	public List<WebElement> musicPadImageIcon;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/iv_PlayPause")
	@iOSXCUITFindBy(accessibility="img_play")
	public WebElement playPauseButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/iv_PlayPause")
	public List<WebElement> playPauseButtonPlayAll;
	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/iv_PlayPause")
	public List<WebElement> mutipleplayPauseButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/sbProgress")
	@iOSXCUITFindBy(accessibility="progressview_play")
	public WebElement progressBar;


	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/iv_PlayPause")
	public List<WebElement> playPauseButton1;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/sbProgress")
	public List<WebElement> progressBar1;

	@AndroidFindBy(xpath = ".//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/rlRow']//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/tvTrackName']")
	@iOSXCUITFindBy(accessibility="collection_cell")
	public List<WebElement> trackNamePad;

	@AndroidFindBy(xpath = ".//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/recyclerview_music_list']//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/tvTrackName']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeTable//XCUIElementTypeCell")
	public List<WebElement> trackListSample;
	
	@iOSXCUITFindBy(accessibility="list_iphone_cell")
	public List<WebElement> trackListText;
	//Calling AppiumDriver.execute() with args: ["mobile: scroll",[{"toVisible":"false","element":"B3000000-0000-0000-7706-000000000000","direction":"down"}],"a7bd741a-2f72-4a51-92a6-911cf7d606be"]
	//localhost:8100/session/CDEBC6D3-D0F1-4C85-BFDA-0EFE9D5A87F9/wda/element/B3000000-0000-0000-7506-000000000000/scroll] with body: {"direction":"down"}
	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_editPad")
	@iOSXCUITFindBy(accessibility="EDIT PADS")
	public WebElement editPads;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_directory_title")
	@iOSXCUITFindBy(accessibility="Free samples")
	public WebElement sampleDirectoryTitle;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_setting")
	public WebElement settingButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_back_settings")
	public WebElement backButtonSetting;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Afrobeats on the iPad\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView")
	public WebElement collectionView;

}
