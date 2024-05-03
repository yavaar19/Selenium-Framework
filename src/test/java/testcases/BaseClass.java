package testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LogIn;
import utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class BaseClass {

    WebDriver driver;

    LogIn logIn;

    ReadConfig readConfig;

    String browser;

    String baseURL;

    public WebDriver initializeDriver() {

        switch (browser) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
        }

        driver.manage().window().maximize();

        return driver;

    }

    public void setConfiguration() throws IOException {

        readConfig = new ReadConfig();

        // Getting system property from Maven command to see if broswer argument is set
        // If set, then used the browser value from the maven command
        // Otherwise, use browser from configuration properties file
        browser = System.getProperty("BROWSER")!=null ? System.getProperty("BROWSER").toLowerCase() :
                readConfig.getBrowser().toLowerCase();

        baseURL = System.getProperty("BASE_URL")!=null ? System.getProperty("BASE_URL").toLowerCase() :
                readConfig.getBaseURL().toLowerCase();

    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        // Read JSON to String
        File src = new File(filePath);
        String jsonContent = FileUtils.readFileToString(src, StandardCharsets.UTF_8);

        // String to HashMap - Jackson Databind
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        return data;

    }

    @BeforeMethod(alwaysRun = true)
    public LogIn launchApplication() throws IOException, InterruptedException {

        setConfiguration();

        driver = initializeDriver();

        logIn = new LogIn(driver);
        logIn.goTo(baseURL);

        return logIn;

    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {

        driver.quit();

    }

}
