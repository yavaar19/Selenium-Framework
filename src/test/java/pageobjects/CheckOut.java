package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOut extends Base {

    @FindBy(css = ".form-group input")
    WebElement countryInput;

    @FindBy(css = ".form-group button span")
    List<WebElement> options;

    @FindBy(css = "div[class='actions'] a")
    WebElement placeOrderButton;

    By countrySelectionFindBy = By.cssSelector(".form-group button span");

    public CheckOut(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void enterCountry(String input) {

        countryInput.sendKeys(input);

    }

    public List<WebElement> getOptions() {

        waitForElementToAppear(countrySelectionFindBy);
        return options;

    }

    public void selectOption(String country) {

        options.stream().filter(option -> option.getText().trim()
                .equalsIgnoreCase(country)).findFirst().orElse(null).click();

    }

    public Confirmation clickPlaceOrderButton() {

        placeOrderButton.click();

        return new Confirmation(driver);

    }

}
