package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.testng.log4testng.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.Slider;

public class SliderSteps extends CucumberRunner
{
	private static Logger log = Logger.getLogger(Slider.class);
	Slider slider = new Slider(driver);

	@When("^I tap on the number beside the green light on the top left$")
	public void iTapOnNumberSlider()
	{
		slider.openSlider();
	}

	@Then("^I should see a slider pop up$")
	public void iShouldSeeSlider()
	{
		assertTrue(slider.verifyOpenBpmSlider());

	}

	@And("^I \"([^\"]*)\" slide to (\\d+), I should see the same number reflect on the app$")
	public void slideToNumner(String operation, int number)
	{
		assertTrue(slider.verifyIncreaseValueBpmSlider(operation, number));
	}

	@And("^I tap on back button, slider pop up should close$")
	public void goBack()
	{
		assertTrue(slider.verifyCloseBpmSlider());
	}
}
