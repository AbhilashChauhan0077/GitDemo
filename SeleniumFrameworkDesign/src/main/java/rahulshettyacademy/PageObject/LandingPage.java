package rahulshettyacademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory:
	
	@FindBy(id="userEmail") // this means  driver.findElement(By.id("userEmail"));  at run time.
	WebElement userEmail; // above step value will initialize in this variable which is just below of the above step.
	
	// to put the driver knowledge in this we have a object initElements in PageFactory class.
	
	@FindBy(id="userPassword") 
	WebElement passwordEle;
	
	@FindBy(id="login") 
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}

}
