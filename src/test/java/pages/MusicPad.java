package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
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

        public MusicPad(AppiumDriver driver) {
                this.driver = driver;
                PageFactory.initElements(new AppiumFieldDecorator(driver), musicPadObjects);
        }

        public boolean verifyMusicPlayPauseAllPad() {

                try {
                        boolean finalResult = true;
                        int numberOfPads = getNumberofusedPads();
                        for (int i = 0; i < numberOfPads; i++) {
                                // Use fresh element lookup to avoid stale element reference
                                List<WebElement> musicPads = driver.findElements(By.id("com.suenare.iafrobeats.afrobeats:id/rlRow"));
                                if (i < musicPads.size()) {
                                        musicPads.get(i).click();
                                }
                                wait(1);
                                boolean play = checkElementDisplayed(musicPadObjects.playPauseButton);
                                if (play) {
                                        findWebElementByIDAndClick(musicPadObjects.playPauseButton);
                                        System.out.println("track play and stop successfully.");
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
                try {
                        // Use fresh element lookup to avoid stale element reference
                        List<WebElement> musicPads = driver.findElements(By.id("com.suenare.iafrobeats.afrobeats:id/rlRow"));
                        if (!musicPads.isEmpty()) {
                                musicPads.get(0).click();
                        }
                } catch (Exception e) {
                        System.out.println("Error clicking music pad: " + e.getMessage());
                        // Fallback to original method
                        findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(0));
                }
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
                try {
                        // Use fresh element lookup to avoid stale element reference
                        List<WebElement> musicPads = driver.findElements(By.id("com.suenare.iafrobeats.afrobeats:id/rlRow"));
                        if (!musicPads.isEmpty()) {
                                musicPads.get(0).click();
                        }
                } catch (Exception e) {
                        System.out.println("Error clicking music pad for pause: " + e.getMessage());
                        // Fallback to original method
                        findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(0));
                }
                wait(3);
        }

        public void pauseMusicSecond() {
                wait(1);
                try {
                        // Use fresh element lookup to avoid stale element reference
                        List<WebElement> musicPads = driver.findElements(By.id("com.suenare.iafrobeats.afrobeats:id/rlRow"));
                        if (musicPads.size() > 1) {
                                musicPads.get(1).click();
                        }
                } catch (Exception e) {
                        System.out.println("Error clicking second music pad for pause: " + e.getMessage());
                        // Fallback to original method
                        findWebElementByIDAndClick(musicPadObjects.musicPadIcon.get(1));
                }
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
                try {
                        // Use fresh element lookup to avoid stale element reference
                        List<WebElement> musicPadImages = driver.findElements(By.xpath(".//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/rlRow']//*[@resource-id='com.suenare.iafrobeats.afrobeats:id/img_music']"));
                        if (musicPadImages.size() > 1) {
                                musicPadImages.get(1).click();
                        }
                } catch (Exception e) {
                        System.out.println("Error clicking multiple pad: " + e.getMessage());
                        // Fallback to original method
                        findWebElementByIDAndClick(musicPadObjects.musicPadImageIcon.get(1));
                }
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
                if (main.Core.getPlatformName().equalsIgnoreCase("Android")) {
                        size = musicPadObjects.musicPadIcon.size();
                } else
                        size = musicPadObjects.trackNamePad.size();
                System.out.println("Total pads" + size);
                return size;
        }

        public int getNumberofusedPads() {
                int size;
                if (main.Core.getPlatformName().equalsIgnoreCase("Android")) {
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
