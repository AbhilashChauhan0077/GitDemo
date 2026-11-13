package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();// this will invoke the webdriver.
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb3")));// wait until the elements on the page is visible.
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		// to get the text of the shopping product and limit the scope of the driver.
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		// to click on the add to cart button of that particular product.
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		// here new css locator is used last-of-type
		// prod here is the full webelement but text is inside the body of this webelement so we started css from body in body there were 
		// two button and we need last button so we used last-of-type if we need first button then we will use first-of-type.
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// wait until the webelement is invisible
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		// click on cart.
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean cart = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cart);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		driver.findElement(By.xpath("(//*[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
	}

}
