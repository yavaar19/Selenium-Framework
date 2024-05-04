package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

public class CheckOut extends Base {

    @FindBy(css = ".form-group input")
    private WebElement countryInput;

    @FindBy(css = ".form-group button span")
    private List<WebElement> options;

    @FindBy(css = "div[class='actions'] a")
    private WebElement placeOrderButton;

    private final By countrySelectionFindBy = By.cssSelector(".form-group button span");

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

        Optional<WebElement> optionalWebElement = options.stream().filter(option -> option.getText().trim()
                .equalsIgnoreCase(country)).findFirst();

        if (optionalWebElement.isPresent()) {

            options.get(0).click();

        }


    }

    public Confirmation clickPlaceOrderButton() {

        placeOrderButton.click();

        return new Confirmation(driver);

    }

}
