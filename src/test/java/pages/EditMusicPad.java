package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.ImeNotAvailableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import main.Core;
import main.CucumberRunner;
import pageObjects.MusicPadObjects;

public class EditMusicPad extends Core {
	HashSet<String> musicTracklist = new HashSet<String>();
	HashSet<String> cartItemlist = new HashSet<String>();
	HashSet<String> cartItemlist2 = new HashSet<String>();
	HashSet<String> musicTracklist2 = new HashSet<String>();
	List<String> valuelistHashset = new ArrayList<String>();
	List<String> musicTracklistHashset = null;
	List<String> CartItemlistHashset = null;
	List<String> sessionList = null;
	int usedPads = 0;
	int totalPads = 0;

	MusicPadObjects musicPadObjects = new MusicPadObjects();
	CucumberRunner cucumberRunner = new CucumberRunner();
	// Properties prop = new Properties();
	// String propFileName = "config.properties";
	// int loopcount = Integer.parseInt(prop.getProperty("IOS_Loop_Count"));
	public EditMusicPad(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), musicPadObjects);
	}

	public void clickEditPads() {
		findWebElementByIDAndClick(musicPadObjects.editPads);
	}

	public boolean checkFolderVisibility() {
		boolean result = checkElementDisplayed(musicPadObjects.sampleDirectoryTitle);
		return result;
	}

	public void clickSampleDirectory() {
		findWebElementByIDAndClick(musicPadObjects.sampleDirectoryTitle);
	}

	public boolean visblityofMusicList() {
		boolean result = checkElementDisplayed(musicPadObjects.musicPadIcon.get(0));
		clickEditPads();
		return result;
	}

	public boolean verifyLoadMusicToPad() {

		try {
			clickEditPads();
			if (!checkElementDisplayed(musicPadObjects.sampleDirectoryTitle)) {
				findWebElementByIDAndClick(musicPadObjects.freesamplesback);

			}
			clickSampleDirectory();
			usedPads = getNumberofusedPads();
			int totalPads = getNumberofTotalPads();
			musicTracklist.clear();
			musicTracklist2.clear();

			if (driver.getPlatformName().equalsIgnoreCase("Android")) {
				try {
					usedPads = getNumberofusedPads();
					int totalPadsAndroid = getNumberofTotalPads();

					int number = musicPadObjects.trackListSample.size();
					int count = 0;
					int startIndex = usedPads;

					while (true) {
						try {
							if (count == number - 1) {
								count = 0;
							}
							if (totalPadsAndroid == getNumberofusedPads()) {
								scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview");
								if (totalPadsAndroid == getNumberofusedPads()) {

									break;

								} else {

									findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(startIndex));
									findWebElementByIDAndClick(musicPadObjects.trackListSample.get((count)));
									count++;
									if (musicPadObjects.trackListSample.get(count).getText()
											.equals(musicPadObjects.trackNamePad.get(startIndex).getText())) {
										System.out.println("Title Verified for added Music Track");
									}
								}

							}

							else {
								findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(startIndex));
								findWebElementByIDAndClick(musicPadObjects.trackListSample.get((count)));

								if (musicPadObjects.trackListSample.get(count).getText()
										.equals(musicPadObjects.trackNamePad.get((startIndex)).getText())) {
									System.out.println("Title Verified for added Music Track");
								}
								if (totalPadsAndroid == getNumberofusedPads()) {
									scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview");

								}
								count++;
								startIndex++;

							}

							if (getNumberofTotalPads() != getNumberofusedPads()) {
								continue;

							} else {
								break;
							}

						} catch (Exception e) {
							scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview");
							startIndex = getNumberofusedPads();
						}
					}

					if (getNumberofTotalPads() == getNumberofusedPads()) {
						return true;
					} else {
						return false;
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}
			} else {
				int moreNumberofPads;

				int numberOfVisible = 0;
				int totalTrackList = musicPadObjects.trackListText.size();
				for (int j = 1; j < totalTrackList; j++) {
					if (!musicPadObjects.trackListText.get(j).isDisplayed())
						break;

					numberOfVisible = numberOfVisible + 1;

				}
				int musicPadLoop = usedPads;
				int trackListLoop = 1;
				int startIndex = usedPads;
				while (true) {
					try {
						if (startIndex != 0)
							findWebElementByIDAndClick(musicPadObjects.trackNamePad.get(startIndex));
					} catch (IndexOutOfBoundsException e) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("element", ((RemoteWebElement) musicPadObjects.collectionView).getId());
						params.put("direction", "down");
						js.executeScript("mobile: scroll", params);
						wait(5);
						// musicPadObjects.trackNamePad.get(startIndex-1).
						startIndex = getNumberofusedPads();
						try {
							findWebElementByIDAndClick(musicPadObjects.trackNamePad.get(startIndex));
						} catch (IndexOutOfBoundsException ie) {
							break;
						}
					}
					if (trackListLoop > numberOfVisible)
						trackListLoop = 1;
					findWebElementByIDAndClick(musicPadObjects.trackListText.get(trackListLoop));
					try {
						new SoftAssert().assertTrue(musicPadObjects.musicPadIcon.get(startIndex).getText()
								.equalsIgnoreCase(musicPadObjects.trackListText.get(trackListLoop).getText()));
					} catch (Exception e) {
						musicPadLoop++;
						trackListLoop++;
						startIndex++;
						continue;
					}
					musicPadLoop++;
					trackListLoop++;
					startIndex++;
				}
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private int getNumberofTotalPads() {
		int size;
		if (driver.getPlatformName().equalsIgnoreCase("Android")) {
			size = musicPadObjects.musicPadIcon.size();
		} else
			size = musicPadObjects.trackNamePad.size();
		System.out.println("Total pads" + size);
		return size;
	}

	private int getNumberofusedPads() {
		int size;
		if (driver.getPlatformName().equalsIgnoreCase("Android")) {
			size = musicPadObjects.trackNamePad.size();
		} else
			size = musicPadObjects.musicPadIcon.size();
		System.out.println("Used pads" + size);
		return size;
	}

	public boolean verifyTitleOFAllLoadedTrack() {
		boolean finalResult;
		try {
			if (driver.getPlatformName().equalsIgnoreCase("Android")) {
				finalResult = true;
				findWebElementByIDAndClick(musicPadObjects.settingButton);
				findWebElementByIDAndClick(musicPadObjects.backButtonSetting);
				for (int i = usedPads, j = 0; i < (usedPads + musicTracklist.size()); i++, j++) {
					if (musicPadObjects.trackNamePad.get(i).getText().equals(musicTracklistHashset.get(j))) {
						System.out.println("Track Added form free sample list and verify same in music pad.");
					} else {
						finalResult = false;
						break;
					}

				}
				return finalResult;
			} else {
				findWebElementByIDAndClick(musicPadObjects.settingButton);
				findWebElementByIDAndClick(musicPadObjects.backButtonSetting);
				if (getNumberofTotalPads() == getNumberofusedPads()) {
					findWebElementByIDAndClick(musicPadObjects.settingButton);
					findWebElementByIDAndClick(musicPadObjects.backButtonSetting);
					finalResult = true;
				} else
					finalResult = false;
				return finalResult;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean playAllAddedTrack() {
		try {
			boolean finalResult = true;
			if (driver.getPlatformName().equalsIgnoreCase("Android")) {
				try {

					findWebElementByIDAndClick(musicPadObjects.settingButton);
					findWebElementByIDAndClick(musicPadObjects.backButtonSetting);
					int usedPADS = getNumberofusedPads();

					for (int i = 0; i <= usedPADS; i++) {
						if (getNumberofusedPads() == i) {

							scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview");
							usedPADS = getNumberofusedPads();
							i = 0;
						}

						if (checkElementDisplayed(musicPadObjects.playPauseButton)) {
							for (int j = 0; i <= usedPADS; i++) {
								findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(i));
								wait(2);
								boolean play = checkElementDisplayed(musicPadObjects.playPauseButton);
								findWebElementByIDAndClick(musicPadObjects.playPauseButton);
								wait(2);
								boolean pause = true;
								if (musicPadObjects.playPauseButtonPlayAll.size() > 1) {
									pause = false;
								}
								if (play && pause) {
									System.out.println("track play and stop succefully.");

								}
							}
							break;
						}

						findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(i));
						wait(1);
						boolean play = checkElementDisplayed(musicPadObjects.playPauseButton);
						findWebElementByIDAndClick(musicPadObjects.playPauseButton);
						wait(1);
						boolean pause = checkElementDisplayed(musicPadObjects.playPauseButton);
						if (play && !pause) {
							System.out.println("track play and stop succefully.");

						}

					}
					return finalResult;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return false;
				}

			} else {

				findWebElementByIDAndClick(musicPadObjects.settingButton);
				findWebElementByIDAndClick(musicPadObjects.backButtonSetting);
				int i = 0;
				for (int z = 0; z <= 150; z++) 
				{

					try {
						findWebElementByIDAndClick(musicPadObjects.trackNamePad.get(i));
						wait(2);
						new SoftAssert().assertTrue(musicPadObjects.playPauseButton.isDisplayed());
						findWebElementByIDAndClick(musicPadObjects.trackNamePad.get(i));
						wait(2);
						new SoftAssert().assertTrue(!musicPadObjects.playPauseButton.isDisplayed());
						i++;
					} catch (IndexOutOfBoundsException e) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("element", ((RemoteWebElement) musicPadObjects.collectionView).getId());
						params.put("direction", "down");
						js.executeScript("mobile: scroll", params);
						i = 0;

					}

				}

				finalResult = true;

			}
			return finalResult;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

}
