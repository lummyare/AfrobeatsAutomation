package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.testng.log4testng.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.NormButton;

public class VolNorm extends CucumberRunner
{
	private static Logger log = Logger.getLogger(VolNorm.class);
	NormButton normButton = new NormButton(driver);

	@When("^I tap the VOL NORM button$")
	public void iTapOnVolNorm()
	{
		normButton.clickOnVolNorm();
	}

	@Then("^assert the button is deactivated$")
	public void verifyButtonUnchecked()
	{
		assertTrue(normButton.verifyVolNormButtonUncheck());
	}

	@And("^when I tap the button again$")
	public void iTapButtonAgain()
	{
		normButton.clickOnVolNorm();
	}

	@Then("^assert the button is activated back$")
	public void verifyButtonCheckedBack()
	{
		assertTrue(normButton.verifyVolNormButtonCheck());
	}
}
