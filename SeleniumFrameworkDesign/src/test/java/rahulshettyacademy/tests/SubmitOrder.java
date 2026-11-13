package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
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
import rahulshettyacademy.PageObject.OrderPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrder extends BaseTest{
	
	String productName = "ZARA COAT 3";
	

		@Test(dataProvider="getData",groups = {"ErrorHandling"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
// TODO Auto-generated method stub
		
		//LandingPage landingPage = launchApplication();
		
//		WebDriverManager.chromedriver().setup();// this will invoke the webdriver.
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://rahulshettyacademy.com/client");		
	    //driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		//driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		//driver.findElement(By.id("login")).click();
		
//		LandingPage landingPage = new LandingPage(driver);		
//		landingPage.goTo();
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb3")));// wait until the elements on the page is visible.
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		
		
		
		// here new css locator is used last-of-type
		// prod here is the full webelement but text is inside the body of this webelement so we started css from body in body there were 
		// two button and we need last button so we used last-of-type if we need first button then we will use first-of-type.
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// wait until the webelement is invisible
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		CartPage cartPage = productCatalogue.goToCart();// it is not defined in ProductCatalogue class still we can call this method from productCatalogue object
		//because of inheritance.

		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	    //Boolean cart = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Boolean match = cartPage.verifyProductDisplay(productName);		
		Assert.assertTrue(match);
		
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
//		
//		driver.findElement(By.xpath("(//*[contains(@class,'ta-item')])[2]")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();
		
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		
		
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		//driver.close();
		
		
		}
		
		@Test(dependsOnMethods={"submitOrder"})
		public void orderHistoryTest()
		{
			// to check "ZARA COAT 3" is available on order history page.
			ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

			OrderPage orderPage = productCatalogue.goToOrder();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
//			@DataProvider
//			public Object[][] getData()
//			{
			//return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"Shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
			//}
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("email", "anshika@gmail.com");
//			map.put("password", "Iamking@000");
//			map.put("productName", "ZARA COAT 3");
//			
//			HashMap<String,String> map1 = new HashMap<String,String>();
//
//			
//			map1.put("email", "Shetty@gmail.com");
//			map1.put("password", "Iamking@000");
//			map1.put("productName", "ADIDAS ORIGINAL");
			
			// from external json file create hashmap or retrieve data in hashmap form in our project
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//Data//PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
			
		}
		
		
		
	}

		


