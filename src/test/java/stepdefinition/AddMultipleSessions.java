package stepdefinition;

import static org.testng.Assert.assertTrue;

import cucumber.api.java.en.Then;
import main.CucumberRunner;
import pages.MultipleSessions;

public class AddMultipleSessions extends CucumberRunner
{

	MultipleSessions multipleSessions = new MultipleSessions(driver);

	@Then("^Tap on Multiple session button on the top right$")
	public void tapOnMultipleSessions()
	{
		if(driver.getPlatformName().equalsIgnoreCase("Android"))
		multipleSessions.multipleSessionSelected();
		else
			System.out.println("Button is already enabled for iOS");
	}

	@Then("^Tap the plus icon create a new Sessions, edit page & repeat scenario (\\d+) times$")
	public void addSession(int number)
	{
		assertTrue(multipleSessions.createMutipleSession(number));
	}
}
