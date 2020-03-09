package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.CartObjects;

public class Cart extends Core {
	HashSet<String> cartItemlist = new HashSet<String>();
	HashSet<String> cartItemlist2 = new HashSet<String>();

	List<String> CartItemlistHashset = null;

	CartObjects cartObjects = new CartObjects();

	public Cart(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), cartObjects);
	}

	private int getNumberofCartItem() throws Exception {
		scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview_store");

		int loop = 1;

		OL: for (int i = 0; i < loop; i++) {

			int number = cartObjects.purchaseItemList.size();

			for (int j = 0; j < number; j++) {
				String itemList = cartObjects.purchaseItemList.get(j).getAttribute("text");
				cartItemlist.add(itemList);
			}

			if (!cartItemlist.equals(cartItemlist2)) {
				scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview_store");
				cartItemlist2.addAll(cartItemlist);
				loop++;
				continue OL;

			} else {
				break;
			}
		}
		System.out.println(cartItemlist);
		CartItemlistHashset = new ArrayList<String>(cartItemlist);
		Collections.sort(CartItemlistHashset);
		System.out.println(CartItemlistHashset);
		return cartItemlist.size();
	}

	public boolean verifyCartCount() {
		try {
			wait(5);
			String cartCount = getText(cartObjects.btnCartCount);
			String expectedCount;
			expectedCount = "40";
			if (cartCount.equals(expectedCount)) {
				System.out.println("Cart Count Verified and Correct");
				return true;
			} else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void clickCartButton() {
		try {
			findWebElementByIDAndClick(cartObjects.btnCart);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean verifyStorePage() {
		try {
			if (checkElementDisplayed(cartObjects.restorePurchaseButton)) {
				return true;
			} else {
				return false;
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyCartPurchase() {
		try {
			// findWebElementByIDAndClick(cartObjects.btnCart);
			// int cartItemCount = getNumberofCartItem();
			findWebElementByIDAndClick(cartObjects.restorePurchaseButton);
			wait(6);
			if (driver.getPlatformName().equalsIgnoreCase("iOS")) {

				if (checkAlertPresent())
					driver.switchTo().alert();
				if (checkElementDisplayed(cartObjects.AppleID))
					cartObjects.AppleID.sendKeys("afbotp1979@gmail.com");
				if (checkElementDisplayed(cartObjects.passwordField))
					cartObjects.passwordField.sendKeys("Test1234!");
				if (checkAlertPresent())
					findWebElementByIDAndClick(cartObjects.alertOK);
			} else
				findWebElementByIDAndClick(cartObjects.confirmPurchase);
			wait(30);

			findWebElementByIDAndClick(cartObjects.backButtonStore);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean goToEditPage() {
		try {
			findWebElementByIDAndClick(cartObjects.editPads);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyPurchaseList() {
		try {
			boolean result = false;

			for (int i = 1; i <= 5; i++) {
				if (cartObjects.sampleDirectoryTitle.size() > 1) {
					result = true;
				}
			}
			findWebElementByIDAndClick(cartObjects.editPads);
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyListItemCount() {
		try {
			if (driver.getPlatformName().equalsIgnoreCase("iOS")) {
				if (cartObjects.itemList.size() == 40) {
					return true;

				} else {
					return false;

				}
			} else
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyPullandRefreshCart() {
		try {
			if (driver.getPlatformName().equalsIgnoreCase("iOS")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("element", ((RemoteWebElement) cartObjects.tableList).getId());
				params.put("direction", "up");
				js.executeScript("mobile: scroll", params);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;

	}
}
