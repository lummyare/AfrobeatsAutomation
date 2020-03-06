package stepdefinition;

import static org.testng.Assert.assertTrue;

import cucumber.api.java.en.Then;
import main.CucumberRunner;
import pages.MultipleSessionsList;

public class VerifyMutipleSessions extends CucumberRunner
{

	MultipleSessionsList multipleSessionslist = new MultipleSessionsList(driver);

	@Then("^go to Multiple session page$")
	public void tapOnMultipleSessions()
	{
		multipleSessionslist.multipleSessionsList();
	}

	@Then("^Verify All Created session are listed and Play each loaded pads on each sessions pages and stop playing all playing pads$")
	public void addSession()
	{
		assertTrue(multipleSessionslist.verifyMutipleSession());
	}
}
