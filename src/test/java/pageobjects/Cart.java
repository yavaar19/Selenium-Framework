package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cart extends Base {

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    private WebElement checkoutButton;

    private final By cartProductTableFindBy = By.cssSelector(".cartSection h3");

    public Cart(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public List<WebElement> getCartProducts() {

        waitForElementToAppear(cartProductTableFindBy);
        return cartProducts;

    }

    public boolean cartContainsProduct(String productName) {

        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

    }

    public CheckOut clickCheckoutButton() {

        checkoutButton.click();
        return new CheckOut(driver);

    }

}
