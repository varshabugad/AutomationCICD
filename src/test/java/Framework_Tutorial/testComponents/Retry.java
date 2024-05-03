package Framework_Tutorial.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count = 0;
	int maxrun=1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxrun)
		
		{
			count++;
			return true;
		}
		return false;
	}

}
