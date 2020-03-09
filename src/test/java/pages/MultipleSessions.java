package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObjects.MainScreenPageObject;
import pageObjects.MusicPadObjects;
import pageObjects.SessionObjects;

public class MultipleSessions extends MusicPad
{

	List<String> sessionList = null;
	SessionObjects sessionObjects = new SessionObjects();
	MainScreenPageObject mainScreenPageObject = new MainScreenPageObject();
	MusicPadObjects musicPadObjects = new MusicPadObjects();
	EditMusicPad editMusicPad = new EditMusicPad(driver);
	public MultipleSessions(AppiumDriver<MobileElement> driver)
	{
		
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		sessionObjects);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		mainScreenPageObject);
		PageFactory.initElements(new AppiumFieldDecorator(driver),
		musicPadObjects);
	}

	public void multipleSessionSelected()
	{
		findWebElementByIDAndClick(sessionObjects.settingButton);
		findWebElementByIDAndClick(sessionObjects.mutipleSessionToggleButton);
		findWebElementByIDAndClick(sessionObjects.backButtonSetting);
	}

	public boolean createMutipleSession(int number)
	{
		try
		{
			boolean result = true;
			for (int i = 1; i <= number; i++)
			{
				findWebElementByIDAndClick(sessionObjects.burgerMenu);
				findWebElementByIDAndClick(sessionObjects.addButtonSessions);
				String newSessionName = "New Session " + i;
				if(driver.getPlatformName().equalsIgnoreCase("iOS")) {
					sessionObjects.sessionNameTextBox.click();
					int num=sessionObjects.sessionNameTextBox.getText().length();
					for (int j = 0; j < num; j++) 
					{
						sessionObjects.sessionNameTextBox.sendKeys(Keys.DELETE);
					}
				
				}
				sessionObjects.sessionNameTextBox.clear();
				sessionObjects.sessionNameTextBox.sendKeys(newSessionName);
				if(driver.getPlatformName().equalsIgnoreCase("iOS"))
					findWebElementByIDAndClick(sessionObjects.openButtonSessions);
				findWebElementByIDAndClick(sessionObjects.openButtonSessions);
				wait(3);
				String sessionName = mainScreenPageObject.mainPageTitle
				.getText();
				if (newSessionName.equals(sessionName))
				{
					
					result = true;
				}
				else
				{
					result = false;
				}
				boolean loadResult = editMusicPad.verifyLoadMusicToPad();
				findWebElementByIDAndClick(musicPadObjects.freesamplesback);
				findWebElementByIDAndClick(musicPadObjects.editPads);	
				boolean playTrackResult = editMusicPad.playAllAddedTrack();

				if (loadResult && playTrackResult && result)
				{
					result = true;
				}
				else
				{
					result = false;
				}
			}

			return result;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void goToMultipleSessions()
	{
		findWebElementByIDAndClick(sessionObjects.btnCart);
	}

	public boolean verifyMutipleSession()
	{
		try
		{
			boolean finalResult = true;

			for (int i = 0; i < sessionList.size(); i++)
			{
				if (sessionList.get(i)
				.equals(sessionObjects.sessionName.get(i)))
				{
					findWebElementByIDAndClick(sessionObjects.sessionName
					.get(i));
					if (sessionObjects.sessionName.get(i).equals(
					sessionObjects.sessionNameTextBox.getText()))

					{
						System.out.println("Created Session Verified");
						findWebElementByIDAndClick(sessionObjects.openButtonSessions);
						for (int j = 0; j < getNumberofusedPads(); j++)
						{
							findWebElementByIDAndClick(musicPadObjects.musicPadIcon
							.get(i));
							wait(1);
							boolean play = checkElementDisplayed(musicPadObjects.playPauseButton);
							findWebElementByIDAndClick(musicPadObjects.playPauseButton);
							wait(1);
							boolean pause = checkElementDisplayed(musicPadObjects.progressBar);
							if (play && pause)
							{
								System.out
								.println("track play and stop succefully.");
							}
							else
							{
								finalResult = false;
								break;
							}

						}
						finalResult = true;
					}
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
