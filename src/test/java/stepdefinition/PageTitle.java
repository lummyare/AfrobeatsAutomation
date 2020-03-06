package stepdefinition;

import static org.junit.Assert.assertTrue;

import org.testng.log4testng.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.MainScreenPage;

public class PageTitle extends CucumberRunner
{
	private static Logger log = Logger.getLogger(PageTitle.class);
	MainScreenPage mainScreenPage;

	@Given("^I launch the App$")
	public void iLaunchTheApp() throws InterruptedException
	{
		mainScreenPage = new MainScreenPage(driver);
		log.info("Waiting for application to get launched");

	}

	@When("^App is launched$")
	public void whenAppIsLaunched()
	{

		log.info("Into App is launched");
	}

	@Then("^Assert the Page name is African Kit$")
	public void verifyPageName()
	{

		assertTrue(mainScreenPage.verifyPageTitle());

		log.info("Into assertion");
	}
}
