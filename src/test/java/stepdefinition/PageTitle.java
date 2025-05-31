package stepdefinition;

import static org.junit.Assert.assertTrue;

import org.testng.log4testng.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
		System.out.println("Waiting for application to get launched");

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
