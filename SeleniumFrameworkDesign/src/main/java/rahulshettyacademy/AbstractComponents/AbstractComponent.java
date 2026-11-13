package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	
	
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	

	public void waitForWebElementToAppear(WebElement x)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(x));
	}

	public void waitForElementToDisappear(By ele) throws InterruptedException
	{
		Thread.sleep(5000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	public CartPage goToCart()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrder()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
