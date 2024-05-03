package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogIn extends Base {

    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="login")
    private WebElement loginButton;

    @FindBy(css="[class*='flyInOut']")
    private WebElement errorToast;

    public LogIn(WebDriver driver) {

        // Bellow we are initializing driver passed as an argument
        // and we are initializing the elements by running the
        // PageFactory providing the driver and the current instance!
        // The PageFactory will automatically trigger the WebElements
        // to go find the element based on the @FindBy locator

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setUserCredentials(String email, String password) {

        userEmail.sendKeys(email);
        userPassword.sendKeys(password);

    }

    public ProductCatalogue logInApplication() {

        loginButton.click();
        return new ProductCatalogue(driver);

    }

    public String getErrorMessage() {

        waitForElementToAppear(errorToast);
        return errorToast.getText();

    }

    public void goTo(String baseURL) {

        driver.get(baseURL);

    }

}
