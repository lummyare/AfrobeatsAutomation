package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.MainScreenPageObject;
import pageObjects.SliderObjects;

public class Slider extends Core
{
	public static String slidervalue = "";

	SliderObjects sliderObjects = new SliderObjects();
	MainScreenPageObject mainScreenPageObject = new MainScreenPageObject();

	public Slider(AppiumDriver<MobileElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		sliderObjects);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		mainScreenPageObject);
	}

	public void openSlider()
	{
		slidervalue = getText(sliderObjects.bpmValue);

		findWebElementByIDAndClick(sliderObjects.bpmValue);
		wait(2);
	}

	public boolean verifyOpenBpmSlider()
	{

		try
		{
			boolean slider = checkElementDisplayed(sliderObjects.bpmSliderMenuValue);
			System.out.println(slider);
			if(driver.getPlatformName().equalsIgnoreCase("iOS"))
				findWebElementByIDAndClick(sliderObjects.bpmValue);
			else
				driver.navigate().back();
			return slider;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyIncreaseValueBpmSlider(String operation, int number)
	{
		boolean result = false;
		try
		{
			findWebElementByIDAndClick(sliderObjects.bpmValue);
			increase_count: while(true)
			{
				String currentBpmValue = getText(sliderObjects.bpmSliderMenuValue);
				String[] test = currentBpmValue.split(" ");

				if (test[0].equals(Integer.toString(number)))
				{
					if(driver.getPlatformName().equalsIgnoreCase("iOS"))
						findWebElementByIDAndClick(sliderObjects.bpmValue);
					else
						driver.navigate().back();
					break increase_count;
				}
				if (operation.equalsIgnoreCase("increase"))
				{
					findWebElementByIDAndClick(sliderObjects.sliderPlusButton);
				}
				else
					findWebElementByIDAndClick(sliderObjects.sliderMinusButton);
			}

			if (getText(sliderObjects.bpmValue).equals(Integer.toString(number)))
			{
				result = true;
			}
			return result;
		}
		catch (

		Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyCloseBpmSlider()
	{
		try
		{

			boolean slider = checkElementDisplayed(sliderObjects.bpmSliderMenuValue);
			return !slider;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
}
