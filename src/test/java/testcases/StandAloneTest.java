package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageobjects.*;

import java.util.List;

public class StandAloneTest extends BaseClass {

//    public static void main(String[] args) throws InterruptedException {
//
//        String productName = "ADIDAS ORIGINAL";
//        String country = "Canada";
//
//        WebDriver driver  = new ChromeDriver();
//        driver.manage().window().maximize();
//
//        LogIn logIn = new LogIn(driver);
//        logIn.goTo(baseURL);
//
//        ProductCatalogue productCatalogue = logIn.logInApplication("yav@gmail.com", "abcDEF123");
//
//        List<WebElement> products = productCatalogue.getProductList();
//
//        WebElement product = productCatalogue.getProductByName(productName);
//
//        productCatalogue.addProductToCart(product);
//
//        Cart cart = productCatalogue.goToCartPage();
//
//        cart.getCartProducts();
//
//        boolean match = cart.cartContainsProduct(productName);
//
//        Assert.assertTrue(match);
//
//        CheckOut checkOut = cart.clickCheckoutButton();
//
//        checkOut.enterCountry("Can");
//
//        checkOut.getOptions();
//
//        checkOut.selectOption(country);
//
//        Confirmation confirmation = checkOut.clickPlaceOrderButton();
//
//        String confirmMessage = confirmation.getThankYouMessage();
//
//        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
//
//
//    }

}
