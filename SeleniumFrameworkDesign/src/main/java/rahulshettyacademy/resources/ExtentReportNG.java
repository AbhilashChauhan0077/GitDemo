package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject()// we are creating this as static because we are not extending this class
	// in other class but we want to use extent of this class so we will send info like classname.methodname
	{
		String path = System.getProperty("user.dir")+"//report//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);//path where we want to see the report.
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul");
		return extent;
	}

}
