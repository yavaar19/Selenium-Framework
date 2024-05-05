package Cucumber.Features.FeatureDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.*;
import testcases.BaseClass;

import java.io.IOException;

public class StepDefinitionImpl extends BaseClass {

    private LogIn logIn;
    private ProductCatalogue productCatalogue;
    private Cart cart;
    private CheckOut checkOut;
    private Confirmation confirmation;

    @Given("I landed on Ecommerce Page")
    public void i_Landed_On_Ecommerce_Page() throws IOException, InterruptedException {

        logIn = launchApplication();

    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {

        logIn.setUserCredentials(username, password);
        productCatalogue = logIn.logInApplication();

    }

    @When("^I add product (.+) to Cart$")
    public void i_add_the_product_to_cart(String productName) {

        productCatalogue.getProductList();
        WebElement product = productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(product);
        cart = productCatalogue.goToCartPage();
        cart.getCartProducts();

    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {

        checkOut = cart.clickCheckoutButton();
        checkOut.enterCountry("Can");
        checkOut.getOptions();
        checkOut.selectOption("Canada");
        confirmation = checkOut.clickPlaceOrderButton();

    }

    @Then("Verify {string} message is displayed on confirmation page")
    public void verify_message_is_displayed_on_confirmation_page(String message) {

        String confirmMessage = confirmation.getThankYouMessage();

        Assert.assertEquals(confirmMessage, message);

    }

    @Then("Verify {string} message is displayed")
    public void verify_message_is_displayed(String message) {

        String errorMessage = logIn.getErrorMessage();

        Assert.assertEquals(errorMessage, message);

    }

}
