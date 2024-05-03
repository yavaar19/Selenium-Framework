package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css = "button[routerlink*='myorders']")
    WebElement ordersButton;

    WebDriver driver;

    WebDriverWait wait;

    By orderButtonFindBy = By.cssSelector("button[routerlink*='myorders']");

    public Base(WebDriver driver) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Cart goToCartPage() {

        try {

            cartButton.click();

        } catch (Exception e) {

            JavascriptExecutor ex = (JavascriptExecutor)driver;
            ex.executeScript("arguments[0].click()", cartButton);

        }

        return new Cart(driver);

    }

    public Order goToMyOrdersPage() {

        waitForElementToAppear(orderButtonFindBy);
        ordersButton.click();

        return new Order(driver);

    }

    public void waitForElementToAppear(By findBy) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));


    }

    public void waitForElementToAppear(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));


    }

    public void waitForElementToDisappear(WebElement element) {

        wait.until(ExpectedConditions.invisibilityOf(element));

    }

}
