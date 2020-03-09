package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.testng.log4testng.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.EditMusicPad;

public class AddFreeSamples extends CucumberRunner
{
	private static Logger log = Logger.getLogger(AddFreeSamples.class);
	EditMusicPad editMusicPad = new EditMusicPad(driver);

	@When("^I tap the Edit page$")
	public void iTapOnEditPad()
	{
		editMusicPad.clickEditPads();
	}

	@Then("^I should see the edit page show a Free Samples folder$")
	public void verifyFolderDisplayed()
	{
		assertTrue(editMusicPad.checkFolderVisibility());
	}

	@And("^I tap to open the folder$")
	public void openFolder()
	{
		editMusicPad.clickSampleDirectory();
	}

	@Then("^I can see audio contents of the folder$")
	public void seeAudioContent() throws Throwable
	{
		assertTrue(editMusicPad.visblityofMusicList());
	}

	@When("^I tap an empty virtual pad and I tap the first audio content in the Free Samples folder and assert the name of the audio content loaded is the same name of the audio content lapped in free sample folder.$")
	public void tapOnEmptyPAD()
	{
		assertTrue(editMusicPad.verifyLoadMusicToPad());
	}

	@Then("^Tap the Edit Pad button again$")
	public void tapOnEditPad()
	{
		editMusicPad.clickEditPads();
	}

	@Then("^tap to play and tap to stop each loaded pads Assert I can see all icons and each loaded pads are playing audio.$")
	public void playAllLoadedMusic()
	{
		assertTrue(editMusicPad.playAllAddedTrack());
	}

}
