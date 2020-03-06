package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CartObjects
{

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/btn_cart")
	@iOSXCUITFindBy(accessibility="btn_navigation_store")
	public WebElement btnCart;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tvBadge")
	@iOSXCUITFindBy(accessibility="40")
	public WebElement btnCartCount;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tvRestorePurchase")
	@iOSXCUITFindBy(accessibility="btn_restore_purchases")
	public WebElement restorePurchaseButton;

	@AndroidFindBy(xpath = ".//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/recyclerview_store']//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/tv_title_name']")
	public List<WebElement> purchaseItemList;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_buy")
	public WebElement buyButton;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/tv_delete")
	public WebElement confirmPurchase;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/img_back")
	@iOSXCUITFindBy(accessibility="btn_navigation_back")
	public WebElement backButtonStore;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_directory_title")
	@iOSXCUITFindBy(accessibility="list_iphone_cell")
	public List<WebElement> sampleDirectoryTitle;

	@AndroidFindBy(id = "com.suenare.iafrobeats.afrobeats:id/txt_editPad")
	@iOSXCUITFindBy(accessibility="EDIT PADS")
	public WebElement editPads;
	
	@iOSXCUITFindBy(accessibility="Apple ID")
	public WebElement AppleID;
	
	@iOSXCUITFindBy(accessibility="Password")
	public WebElement passwordField;
	
	@iOSXCUITFindBy(accessibility="OK")
	public WebElement alertOK;
	
	@iOSXCUITFindBy(accessibility="list_store_cell")
	public List<WebElement> itemList;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeApplication[@name=\"Afrobeats on the iPad\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable")
	public WebElement tableList;
	
	@iOSXCUITFindBy(accessibility="btn_purchase")
	public List<WebElement> purchaseButton;
}
