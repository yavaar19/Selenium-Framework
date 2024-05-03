package testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
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

    protected WebDriver driver;

    protected LogIn logIn;

    protected ReadConfig readConfig;

    private String browser;

    private String baseURL;

    public WebDriver initializeDriver() {

        if (browser.contains("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (browser.contains("headless")) options.addArguments("headless");

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browser.contains("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (browser.contains("headless")) options.addArguments("headless");

            driver = new FirefoxDriver(options);

        } else if (browser.contains("safari")) {

            driver = new SafariDriver();

        }

        driver.manage().window().maximize();

        return driver;

    }

    public void setConfiguration() throws IOException {

        readConfig = new ReadConfig();

        // Getting system property from Maven command to see if browser argument is set
        // If set, then used the browser value from the maven command
        // Otherwise, use browser from configuration properties file
        browser = System.getProperty("browser")!=null ? System.getProperty("browser").toLowerCase() :
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
