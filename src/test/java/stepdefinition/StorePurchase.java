package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.CucumberRunner;
import pages.Cart;

public class StorePurchase extends CucumberRunner
{
	private static Logger log = Logger.getLogger(VolNorm.class);
	Cart cart = new Cart(driver);

	@And("^The assert the store button is showing store count 40$")
	public void verifyCartCount()
	{
		assertTrue(cart.verifyCartCount());
	}

	@When("^I tap on the store count$")
	public void tapCartButton()
	{
		cart.clickCartButton();
	}

	@When("^I see the store page$")
	public void verifyStorePage()
	{
		assertTrue(cart.verifyStorePage());
	}

	@When("^I tap on RESTORE PURCHASES button and purchase all items$")
	public void restorePurchase()
	{
		assertTrue(cart.verifyCartPurchase());
	}

	@Then("^Exit Store page and Go to Edit Page$")
	public void navigateToEditPage()
	{
		cart.goToEditPage();
	}

	@Then("^Assert all Folder names showing are all contents downloaded from Store page$")
	public void verifyListOnEditPads()
	{
		new SoftAssert().assertTrue(cart.verifyPurchaseList());
	}
	
	@Then("^I refresh the page$")
	public void refreshList() {
		cart.verifyPullandRefreshCart();
	}
	
	@And("^I should see 40 items in list$")
	public void verifyItemList()
	{
		assertTrue(cart.verifyListItemCount());
	}

}
