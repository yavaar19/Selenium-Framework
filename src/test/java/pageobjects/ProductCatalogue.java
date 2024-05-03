package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends Base {

    @FindBy(css = ".mb-3")
    private List<WebElement> productElements;

    @FindBy(css = ".ng-animating")
    private WebElement loadingAnimation;

    private final By productListFindBy = By.cssSelector(".mb-3");

    private final By productAddToCartButtonFindBy = By.cssSelector("div.card-body button:last-of-type");

    private final By toastConfirmationFindBy = By.id("toast-container");

    public ProductCatalogue(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public List<WebElement> getProductList() {

        waitForElementToAppear(productListFindBy);
        return productElements;

    }

    public WebElement getProductByName(String productName) {

        return productElements.stream()
                .filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

    }

    public void addProductToCart(WebElement product) {

        product.findElement(productAddToCartButtonFindBy).click();
        waitForElementToAppear(toastConfirmationFindBy);
        waitForElementToDisappear(loadingAnimation);

    }

}
