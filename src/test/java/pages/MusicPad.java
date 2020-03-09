package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.Core;
import pageObjects.MusicPadObjects;

public class MusicPad extends Core {

	public static int usedPads = 0;

	HashSet<String> musicTracklist = new HashSet<String>();
	MusicPadObjects musicPadObjects = new MusicPadObjects();
	List<String> musicTracklistHashset = null;
	HashSet<String> musicTracklist2 = new HashSet<String>();
	List<String> valuelistHashset = new ArrayList<String>();

	public MusicPad(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), musicPadObjects);
	}

	public boolean verifyMusicPlayPauseAllPad() {

		try {
			boolean finalResult = true;
			for (int i = 0; i < (getNumberofusedPads()); i++) {
				findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(i));
				wait(1);
				boolean play = checkElementDisplayed(musicPadObjects.playPauseButton);
				findWebElementByIDAndClick(musicPadObjects.playPauseButton);
				if (play) {
					System.out.println("track play and stop succefully.");
				} else {
					finalResult = false;
				}

			}
			return finalResult;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void playMusic() {
		// driver.findElement(By.xpath("(//XCUIElementTypeImage[@name=\"pad_loaded\"])[1]"));
		findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(0));
		wait(3);
	}

	public boolean verifyMusicPlay() {
		boolean play;
		try {
			play = checkElementDisplayed(musicPadObjects.playPauseButton);
			return play;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void pauseMusic() {
		wait(1);
		/*
		 * if(driver.getPlatformName().equalsIgnoreCase("iOS")) {
		 * findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(0)); } else
		 */
		findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(0));
		wait(3);
	}

	public void pauseMusicSecond() {
		wait(1);
		/*
		 * if(driver.getPlatformName().equalsIgnoreCase("iOS")) {
		 * findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(0)); } else
		 */
		findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(1));
		wait(3);
	}

	public boolean verifyMusicPause() {
		boolean play;

		try {

			play = !checkElementDisplayed(musicPadObjects.playPauseButton);
			System.out.println(play);
			return play;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void multiplePadPlay() {
		findWebElementByIDAndClick(musicPadObjects.musicPadImageIcon.get(1));
		wait(3);
	}

	public boolean verifyOverlapMusicPlay() {

		try {
			boolean play;

			if (checkElementDisplayed(musicPadObjects.playPauseButton)) {
				play = true;
			} else {
				play = false;
			}
			return play;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyTitleOFAllLoadedTrack() {
		try {
			boolean finalResult = true;
			for (int i = usedPads, j = 0; i < (usedPads + musicTracklist.size()); i++, j++) {
				if (musicPadObjects.trackNamePad.get(i).getText().equals(musicTracklistHashset.get(j))) {
					finalResult = true;
				} else {
					finalResult = false;
					break;
				}
			}
			return finalResult;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getNumberofTotalPads() {
		int size;
		if (driver.getPlatformName().equalsIgnoreCase("Android")) {
			size = musicPadObjects.musicPadIcon.size();
		} else
			size = musicPadObjects.trackNamePad.size();
		System.out.println("Total pads" + size);
		return size;
	}

	public int getNumberofusedPads() {
		int size;
		if (driver.getPlatformName().equalsIgnoreCase("Android")) {
			size = musicPadObjects.trackNamePad.size();
		} else
			size = musicPadObjects.musicPadIcon.size();
		System.out.println("Used pads" + size);
		return size;
	}

	private int getNumberofTrackSample() throws Exception {

		int loop = 1;

		OL: for (int i = 0; i < loop; i++) {

			int number = musicPadObjects.trackListSample.size();

			for (int j = 0; j < number; j++) {
				musicTracklist.add(musicPadObjects.trackListSample.get(j).getAttribute("text"));
			}

			if (!musicTracklist.equals(musicTracklist2)) {
				scroll("com.suenare.iafrobeats.afrobeats:id/recyclerview_music_list");
				musicTracklist2.addAll(musicTracklist);
				loop++;
				continue OL;

			} else {
				break;
			}
		}
		System.out.println(musicTracklist);
		musicTracklistHashset = new ArrayList<String>(musicTracklist);
		Collections.sort(musicTracklistHashset);
		System.out.println(musicTracklistHashset);
		return musicTracklist.size();
	}

}
