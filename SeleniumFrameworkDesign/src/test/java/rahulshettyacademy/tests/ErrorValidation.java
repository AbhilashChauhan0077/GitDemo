package rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.CheckOutPage;
import rahulshettyacademy.PageObject.ConfirmationPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidation extends BaseTest{

		@Test(retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException, InterruptedException
		{

		String productName = "ZARA COAT 3";

		landingPage.loginApplication("anshika@gmail.com", "231@");
		Assert.assertEquals("Incorrect emaillll or password.",landingPage.getErrorMessage());
		
		}
		
		@Test
		public void productErrorValidation() throws InterruptedException
		{
			String productName = "ZARA COAT 3";
			ProductCatalogue productCatalogue=landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCart();
			Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");		
			Assert.assertFalse(match);
			
		}
		
	}


