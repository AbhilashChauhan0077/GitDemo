package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.CheckOutPage;
import rahulshettyacademy.PageObject.ConfirmationPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommetrce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);

	}

	@When("^I add product (.+) to cart.$")
	public void I_add_product_to_cart(String productname) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
	}

	@And("^checkout (.+) and submit the order.$")
	public void Chectout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
	}

	@Then("{String} message is displayed on confirmation page.")
	
	public void message_displayed_on_confirmation_page(String exp_message) {
		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(exp_message));
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void Message_is_displayed(String message)
	{
		Assert.assertEquals(message,landingPage.getErrorMessage());
		driver.close();
	}

}
