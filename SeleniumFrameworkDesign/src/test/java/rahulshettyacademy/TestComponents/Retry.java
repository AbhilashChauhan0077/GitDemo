package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{// after listening to listeners it will 
	//reaches here if test will fail and ask here do i need to rerun this test again.
	
	// when the test will be fail we can identify from result which test is failing so we need to add 
	//@Test(retryAnalyzer=classname.class)-- to that test case which is failing then only it will work.
	
	int count=0;
	int maxTry=2;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;// it means it will stop
	}

}
