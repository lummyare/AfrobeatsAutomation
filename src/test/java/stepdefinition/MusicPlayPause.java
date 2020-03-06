package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.MusicPad;

public class MusicPlayPause extends CucumberRunner
{
	SoftAssert softAssert = new SoftAssert();
	private static Logger log = Logger.getLogger(MusicPlayPause.class);
	MusicPad musicPad = new MusicPad(driver);

	@When("^I tap on music pad$")
	public void iTapOnMusicPad()
	{
		musicPad.playMusic();
	}

	@And("^I Tap to play and tap to stop playing each loaded pads$")
	public void playandPauseAllTrack()
	{
		musicPad.verifyMusicPlayPauseAllPad();
	}

	@Then("^Music should be played & progress bar should be displayed$")
	public void musicShouldBePlayed()
	{
		softAssert.assertTrue(musicPad.verifyMusicPlay(),"Play button is not displayed");
	}

	@And("^I tap on pause$")
	public void iTapOnPause()
	{
		musicPad.pauseMusic();
	}

	@Then("^Music should be paused & progress bar should be removed$")
	public void musicShouldBePuased()
	{
		softAssert.assertTrue(musicPad.verifyMusicPause(),"Music is not paused");
	}

	@And("^I tap on 2nd music pad$")
	public void iTapOnSecondPad()
	{
		musicPad.multiplePadPlay();
	}

	@Then("^Music should be played for 2nd pad$")
	public void musicMultiplePad()
	{
		softAssert.assertTrue(musicPad.verifyOverlapMusicPlay(),"Multiple pads are not played properly");
	}
	
	@And("^I tap on pause for 2nd pad$")
	public void iPasueSecondPad()
	{
		musicPad.pauseMusicSecond();
	}
}
