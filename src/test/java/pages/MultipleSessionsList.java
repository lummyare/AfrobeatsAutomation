package pages;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObjects.MainScreenPageObject;
import pageObjects.MusicPadObjects;
import pageObjects.SessionObjects;

public class MultipleSessionsList extends MusicPad
{

	//List<String> sessionList = null;
	SessionObjects sessionObjects = new SessionObjects();
	MainScreenPageObject mainScreenPageObject = new MainScreenPageObject();
	MusicPadObjects musicPadObjects = new MusicPadObjects();

	public MultipleSessionsList(AppiumDriver<MobileElement> driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		sessionObjects);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		mainScreenPageObject);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		musicPadObjects);
	}

	public void multipleSessionsList()
	{
		findWebElementByIDAndClick(sessionObjects.burgerMenu);
	}

	public boolean verifyMutipleSession()
	{

		try
		{
			boolean finalResult = true;
			for (int i = 0; i < sessionObjects.sessionName.size(); i++)
			{

				findWebElementByIDAndClick(sessionObjects.sessionName.get(i));
				if (sessionObjects.sessionName.get(i).equals(sessionObjects.sessionNameTextBox.getText()))

				{
					System.out.println("Created Session Verified");
					findWebElementByIDAndClick(sessionObjects.openButtonSessions);
					EditMusicPad ed = new EditMusicPad(driver);
					finalResult = ed.playAllAddedTrack();
				}

				else
				{
					finalResult = false;
					break;
				}
			}
			return finalResult;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

}
