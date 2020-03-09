package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;

import org.openqa.selenium.support.PageFactory;

import pageObjects.NormButtonObjects;

public class NormButton extends Core {

	String btnStatusBefore;
	NormButtonObjects normButtonObjects = new NormButtonObjects();

	public NormButton(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), normButtonObjects);
	}

	public void clickOnVolNorm() {
		if (driver.getPlatformName().equalsIgnoreCase("ios")) {
			findWebElementByIDAndClick(normButtonObjects.volNormButton);

		} else {
			btnStatusBefore = getAttribute(normButtonObjects.volNormButton, "selected");
			findWebElementByIDAndClick(normButtonObjects.volNormButton);
		}
	}

	public boolean verifyVolNormButtonUncheck() {
		boolean result = true;
		try {

			if (driver.getPlatformName().equalsIgnoreCase("ios")) {

				if (isAttribtuePresent(normButtonObjects.volNormButton, "value")) {
					result = false;
				} else {
					result = true;
				}

				return result;
			} else {
				String btnStatusAfter = getAttribute(normButtonObjects.volNormButton, "selected");
				if (btnStatusAfter.equals("false") && btnStatusBefore.equals("true")) {
					System.out.println("Clicked and deselect succesffuly. ");
					return true;
				} else {
					System.out.println("Not Clicked and deselect succesffuly. ");

					return false;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyVolNormButtonCheck() {
		boolean result = true;

		try {
			if (driver.getPlatformName().equalsIgnoreCase("ios")) {
				System.out.println(getAttribute(normButtonObjects.volNormButton, "value"));
				if (getAttribute(normButtonObjects.volNormButton, "value").equals("1")) {
					result = true;
				} else {
					result = false;
				}

				return result;
			} else {
				String btnStatusAfter = getAttribute(normButtonObjects.volNormButton, "selected");
				if (btnStatusAfter.equals("true") && btnStatusBefore.equals("false")) {
					System.out.println("Clicked and select succesffuly. ");

					return true;
				} else {
					System.out.println("Not Clicked and select succesffuly. ");

					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
