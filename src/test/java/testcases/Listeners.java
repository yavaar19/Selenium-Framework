package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Screenshot;

import java.io.IOException;

import static utilities.ExtentReporterNG.getReportObject;

public class Listeners extends Screenshot implements ITestListener {

    private ExtentTest test;
    private ExtentReports extent = getReportObject();
    private WebDriver driver;

    // ThreadLocal allows test to run in parallel
    // without having overlap between test reporting
    // Each ExtentTest will have it's own thread ID
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);

        test = extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ITestListener.super.onTestSuccess(result);

        threadLocal.get().log(Status.PASS, "Test passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        threadLocal.get().fail(result.getThrowable());
        String filePath;

        // Get current driver from result argument
        Object currentClass = result.getInstance();
        driver = ((ErrorValidations) currentClass).driver;

        try {

            filePath = getScreenshot(result.getMethod().getMethodName(), driver);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        threadLocal.get().addScreenCaptureFromPath(filePath, result.getTestName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }

}
