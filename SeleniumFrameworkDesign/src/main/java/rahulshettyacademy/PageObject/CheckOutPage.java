package rahulshettyacademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//*[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".hero-primary")
	WebElement z;
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;

	}

}
