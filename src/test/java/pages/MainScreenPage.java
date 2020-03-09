package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.MainScreenPageObject;

public class MainScreenPage extends Core
{

	HashSet<String> musicTracklist = new HashSet<String>();
	List<String> sessionList = null;
	MainScreenPageObject mainScreenPageObject = new MainScreenPageObject();

	public MainScreenPage(AppiumDriver<MobileElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		mainScreenPageObject);
	}

	public boolean verifyPageTitle()
	{
		try
		{
			String pageTitle = getText(mainScreenPageObject.mainPageTitle);
			sessionList = new ArrayList<String>();
			sessionList.add(pageTitle);
			String Msg = "African Kit";
			boolean strtxt = pageTitle.equalsIgnoreCase(Msg);
			System.out.println(strtxt);
			return strtxt;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
}
