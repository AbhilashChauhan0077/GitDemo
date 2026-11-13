package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.LandingPage;

public class BaseTest {
	
	// to set global properties we have a Properties class which will parse the file with .properties extension like we created
	// in main "GlobalData.properties.
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		//System.getProperty("user.dir")- it will automatically take your local system project path.
		// and put // in the next path so that java recognizes that as a path.
		prop.load(fis);// this want input stream so we need to change our file to stream and we write a class above this line
		// which convert file to input stream.
		// in prop.load we load .properties file and this method parse the file and extract all key value pair from it.
		String browserName = System.getProperty("browsernam")!=null ? System.getProperty("browsernam") : prop.getProperty("browser");
		
		// to run the test cases from maven we need to add plugin "maven testng integration" in pom.xml file.
		// in this plugin <suiteXMLFile> tag will check the location of file from the project level which we need to run.
		/*if we need to run different tests then we will use
		 * <profiles>
		 * <profile>
		 * <id>Regression</id>
		 * copy full code of maven testng integration plugin from <build>
		 * at the end just change the name of file which we need to run inside <<suiteXMLFile> testSuites/testng.xml<suiteXMLFile>
		 * and close all the tags till profiles*/
		// mvn test -PRegression---> this terminal command means it will run only regression id from profile.
		// mvn test -PRegression -Dbrowsername=firefox ---> this terminal command means it will check inside the parameters 
		
		
		// java -jar jenkins.war --enable-future-java -httpport=8080--> we are asking java to open the jar then name 
		//of file  then port no where we want to launch.
		//create a new job>put item name >free style>it will reach to general where we need to write everything>
		//advance>use custom workspace>put the path of your project
		//build>invoke top level maven target>then
		// provide maven command in goal and we do not require mvn> then select build now to run the code.
		// to reach to general we need to click on configure>this project is parameterized>
		//parameter>choice> name "browser" i.e parameter> choice "chrome"> now build will change to build with parameters
		// change to build with parameters>in command -P"$Profile" -DBrowser="$browsername"
		
		//to run your job automatically from jenkins go to build triggers> select build periodically
		if (browserName.contains("chrome"))
				{
			      ChromeOptions options = new ChromeOptions();
			         

				
			
					if(browserName.contains("headless"))
					{
					options.addArguments("--headless=old");// execution will be done at the back end without opening chrome
					}
					WebDriverManager.chromedriver().setup();// this will invoke the webdriver.
					
					
			          driver = new ChromeDriver(options);
				         driver.manage().window().setSize(new Dimension(1440,900));
				         
				         //MINUTES Minutes in one hour (0-59)-->if we put H in this then it will trigger once in that hour.
				         //HOURS Hours in one day(0-23)
				         //DAYMONTH day in a month(1-31)
				         //MONTH Month in a year(1-12)
				         //DAYWEEK Day of the week(0-7) where 0 and 7 are sunday.
				         // but we need to keep our server on 
				         

				}
		if (browserName.equalsIgnoreCase("firefox"))
		{
	         WebDriverManager.firefoxdriver().setup();// this will invoke the webdriver.
	          driver = new FirefoxDriver();
		}
		
		if (browserName.equalsIgnoreCase("edge"))
		{
	         WebDriverManager.edgedriver().setup();// this will invoke the webdriver.
	          driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();
		return driver;
	}
	
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
			// read json file to string
			String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			
			// read string to hashmap:  -- to use ObjectMapper we need a dependency jackson databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
			return data;
		}
		
		@BeforeMethod(alwaysRun=true)
		public LandingPage launchApplication() throws IOException
		{
			driver = initializeDriver();
			 landingPage = new LandingPage(driver);		
			landingPage.goTo();
			return landingPage;
			
		}
		
		@AfterMethod(alwaysRun=true)
		public void tearDown()
		{
			driver.close();
		}
		
		public String getScreenshot(String testcasename,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+ "//reportsfolder//" + testcasename + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+ "//reportsfolder//" + testcasename + ".png";
		}
		
		
				
	}


