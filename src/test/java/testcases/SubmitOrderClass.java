package testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;

public class SubmitOrderClass extends BaseClass {

    private String productName = "ADIDAS ORIGINAL";
    private String orderNumber;

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(String username, String password, String productName, String country) throws IOException {

        logIn.setUserCredentials(username, password);
        ProductCatalogue productCatalogue = logIn.logInApplication();

        productCatalogue.getProductList();

        WebElement product = productCatalogue.getProductByName(productName);

        productCatalogue.addProductToCart(product);

        Cart cart = productCatalogue.goToCartPage();

        cart.getCartProducts();

        boolean match = cart.cartContainsProduct(productName);

        Assert.assertTrue(match);

        CheckOut checkOut = cart.clickCheckoutButton();

        checkOut.enterCountry("Can");

        checkOut.getOptions();

        checkOut.selectOption(country);

        Confirmation confirmation = checkOut.clickPlaceOrderButton();

        String confirmMessage = confirmation.getThankYouMessage();
        orderNumber = confirmation.getOrderNumber();

        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {

        logIn.setUserCredentials(readConfig.getUsername(), readConfig.getPassword());
        logIn.logInApplication();

        Order order = logIn.goToMyOrdersPage();

        order.getOrderedProducts();

        Assert.assertTrue(order.orderHistoryContainsOrderAndProduct(orderNumber, productName));

    }

    // DataProvider using two dimensional array
    @DataProvider
    public Object[][] getData() {

        return new Object[][] {{"yav@gmail.com", "abcDEF123", "ADIDAS ORIGINAL", "Canada"}};

    }


//    // DataProvider example with HashMap
//
//    @Test(dataProvider = "getData", groups = {"Purchase"})
//    public void submitOrder(HashMap<String, String> input) {
//
//        logIn.setUserCredentials(input.get("email"), input.get("password"));
//        ProductCatalogue productCatalogue = logIn.logInApplication();
//
//        productCatalogue.getProductList();
//
//        WebElement product = productCatalogue.getProductByName(input.get("product"));
//
//        productCatalogue.addProductToCart(product);
//
//        Cart cart = productCatalogue.goToCartPage();
//
//        cart.getCartProducts();
//
//        boolean match = cart.cartContainsProduct(input.get("product"));
//
//        Assert.assertTrue(match);
//
//        CheckOut checkOut = cart.clickCheckoutButton();
//
//        checkOut.enterCountry("Can");
//
//        checkOut.getOptions();
//
//        checkOut.selectOption(input.get("country"));
//
//        Confirmation confirmation = checkOut.clickPlaceOrderButton();
//
//        String confirmMessage = confirmation.getThankYouMessage();
//        orderNumber = confirmation.getOrderNumber();
//
//        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
//
//    }
//
//    // DataProvider using hashmap
//    @DataProvider
//    public Object[][] getData() {
//
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("email", "yav@gmail.com");
//        map.put("password", "abcDEF123");
//        map.put("product", "ADIDAS ORIGINAL");
//        map.put("country", "Canada");
//
//        HashMap<String, String> map1 = new HashMap<>();
//
//        map1.put("email", "yav@gmail.com");
//        map1.put("password", "abcDEF123");
//        map1.put("product", "ADIDAS ORIGINAL");
//        map1.put("country", "Canada");
//
//        return new Object[][] {{map}, {map1}};
//
//    }

//    // DataProvider example of creating Hashmap
//    // from JSON file
//
//    @Test(dataProvider = "getData", groups = {"Purchase"})
//    public void submitOrder(HashMap<String, String> input) {
//
//        logIn.setUserCredentials(input.get("email"), input.get("password"));
//        ProductCatalogue productCatalogue = logIn.logInApplication();
//
//        productCatalogue.getProductList();
//
//        WebElement product = productCatalogue.getProductByName(input.get("product"));
//
//        productCatalogue.addProductToCart(product);
//
//        Cart cart = productCatalogue.goToCartPage();
//
//        cart.getCartProducts();
//
//        boolean match = cart.cartContainsProduct(input.get("product"));
//
//        Assert.assertTrue(match);
//
//        CheckOut checkOut = cart.clickCheckoutButton();
//
//        checkOut.enterCountry("Can");
//
//        checkOut.getOptions();
//
//        checkOut.selectOption(input.get("country"));
//
//        Confirmation confirmation = checkOut.clickPlaceOrderButton();
//
//        String confirmMessage = confirmation.getThankYouMessage();
//        orderNumber = confirmation.getOrderNumber();
//
//        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
//
//    }
//
//    // DataProvider using hashmap
//    // creating Hashmap from JSON file
//    @DataProvider
//    public Object[][] getData() throws IOException {
//
//        HashMap<String, String> map = new HashMap<>();
//
//        List<HashMap<String, String>> data = getJsonDataToMap("src/test/java/data/PurchaseOrder.json");
//
//        return new Object[][] {{data.get(0)}, {data.get(1)}};
//
//    }

}
