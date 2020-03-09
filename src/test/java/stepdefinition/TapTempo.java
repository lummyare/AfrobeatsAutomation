package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.testng.log4testng.Logger;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.TapTempoButton;

public class TapTempo extends CucumberRunner
{
	private static Logger log = Logger.getLogger(VolNorm.class);
	TapTempoButton tapTempoButton = new TapTempoButton(driver);

	@When("^I enable Tap Tempo button from Settings$")
	public void enableTapTempo()
	{
		if(driver.getPlatformName().equalsIgnoreCase("Android"))
		tapTempoButton.enableTapTempoCheckBox();
		else
			System.out.println("Button is already enabled for iOS");
	}

	@Then("^I tap on Tap Tempo button multiple times$")
	public void iTapOnTapTempoButton()
	{
		tapTempoButton.multipleClickTapTempoButton();
	}

	@Then("^Tap tempo button should be deactivated$")
	public void verifyButtonDeactivated()
	{
		assertTrue(tapTempoButton.verifyTapTempoButton());
	}

}
