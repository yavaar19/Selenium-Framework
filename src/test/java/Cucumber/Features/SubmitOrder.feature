@SubmitOrderFeature
Feature: Purchase a product from website

    # Background is similar to before method in testNG! It will everytime before every test!
    Background:
      Given I landed on Ecommerce Page

    @SubmitOrder
    Scenario Outline: Positive test of submitting order
      Given Logged in with username <name> and password <password>
      When  I add product <productName> to Cart
      And   Checkout <productName> and submit the order
      Then  Verify "THANKYOU FOR THE ORDER." message is displayed on confirmation page

      Examples:
      | name          | password  | productName |
      | yav@gmail.com | abcDEF123 | ZARA COAT 3 |