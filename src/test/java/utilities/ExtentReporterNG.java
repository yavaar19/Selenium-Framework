package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {

        ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/reports/index.html");

        // Setting report configuration
        reporter.config().setReportName("Web Automation Result");
        reporter.config().setDocumentTitle("Test Results");

        // Need to attach ExtentSparkReporter to ExtentReport to
        // consolidate all test to same report
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Set tester info
        extent.setSystemInfo("Tester", "Yavaar Nosimohomed");

        return extent;

    }

}
