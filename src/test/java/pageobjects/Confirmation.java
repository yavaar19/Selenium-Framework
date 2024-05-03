package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation extends Base {

    @FindBy(css = ".hero-primary")
    WebElement thankYouMessage;

    @FindBy(css = "label.ng-star-inserted")
    WebElement orderNumber;

    By thankYouMessageFindBy = By.cssSelector(".hero-primary");

    public Confirmation(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public String getThankYouMessage() {

        waitForElementToAppear(thankYouMessageFindBy);
        return thankYouMessage.getText().toUpperCase();

    }

    public String getOrderNumber() {

        return orderNumber.getText().split(" ")[1];

    }

}
