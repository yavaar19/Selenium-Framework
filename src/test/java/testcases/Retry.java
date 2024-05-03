package testcases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTries = 2;


    // Test will always retry so long as the method returns
    // true
    @Override
    public boolean retry(ITestResult iTestResult) {

        if (count < maxTries) {

            count++;
            return true;

        }

        return false;

    }

}
