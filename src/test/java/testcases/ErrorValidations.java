package testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.Cart;
import pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseClass {

    // Setting retryAnalyzer will trigger the retry listener class
    // to retry on failure! Only apply retries to test that are
    // found to be flaky
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void logInErrorValidation() {

        logIn.setUserCredentials("sadasd@gmail.com", "12345");
        logIn.logInApplication();
        Assert.assertEquals(logIn.getErrorMessage(), "Incorrect email password.");

    }

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void productErrorValidation() {

        String productName = "ADIDAS ORIGINAL";

        logIn.setUserCredentials(readConfig.getUsername(), readConfig.getPassword());
        ProductCatalogue productCatalogue = logIn.logInApplication();

        productCatalogue.getProductList();

        WebElement product = productCatalogue.getProductByName(productName);

        productCatalogue.addProductToCart(product);

        Cart cart = productCatalogue.goToCartPage();

        cart.getCartProducts();

        boolean match = cart.cartContainsProduct("ADIDAS ORIGINAL 33");

        Assert.assertFalse(match);

    }

}
