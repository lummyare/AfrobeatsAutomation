package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.MainScreenPageObject;

public class MainScreenPage extends Core
{

        HashSet<String> musicTracklist = new HashSet<String>();
        List<String> sessionList = null;
        MainScreenPageObject mainScreenPageObject = new MainScreenPageObject();

        public MainScreenPage(AppiumDriver driver)
        {
                this.driver = driver;
                PageFactory.initElements(new AppiumFieldDecorator(driver),
                mainScreenPageObject);
        }

        public boolean verifyPageTitle()
        {
                try
                {
                        // Add explicit wait for element to be present
                        Thread.sleep(3000);
                        String pageTitle = getText(mainScreenPageObject.mainPageTitle);
                        sessionList = new ArrayList<String>();
                        sessionList.add(pageTitle);
                        String expectedMsg = "African Kit";
                        
                        System.out.println("Expected page title: " + expectedMsg);
                        System.out.println("Actual page title: " + pageTitle);
                        
                        boolean strtxt = pageTitle.equalsIgnoreCase(expectedMsg);
                        System.out.println("Title match result: " + strtxt);
                        return strtxt;
                }
                catch (Exception e)
                {
                        System.out.println("Exception in verifyPageTitle: " + e.getMessage());
                        e.printStackTrace();
                        return false;
                }
        }
}
