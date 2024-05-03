package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Order extends Base {

    @FindBy(css = "tr.ng-star-inserted")
    private List<WebElement> orderedProducts;

    private final By orderRowsFindBy = By.cssSelector("tr.ng-star-inserted");

    private final By orderNumberFindBy = By.cssSelector("th");

    private final By productNameFindBy = By.cssSelector("td:nth-child(3)");

    public Order(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public List<WebElement> getOrderedProducts() {

        waitForElementToAppear(orderRowsFindBy);
        return orderedProducts;

    }

    public boolean orderHistoryContainsOrderAndProduct(String orderNumber, String productName) {

        List<WebElement> collect = orderedProducts.stream().filter(row -> row.findElement(orderNumberFindBy)
                        .getText().equals(orderNumber))
                .filter(product -> product.findElement(productNameFindBy).getText().equals(productName))
                .toList();

        return !collect.isEmpty();

    }


}
