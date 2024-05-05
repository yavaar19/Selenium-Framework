package Cucumber.Features;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// This is the cucumber wrapper. It allows testNG and cucumber to work together!
@CucumberOptions(features = "src/test/java/Cucumber/Features", glue = "Cucumber.Features.FeatureDefinitions",
        monochrome = true, tags = "@ErrorValidationFeature or @SubmitOrderFeature", plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {



}
