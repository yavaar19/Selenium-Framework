@ErrorValidationFeature
Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Error message is displayed with wrong username and password
    Given I landed on Ecommerce Page
    When  Logged in with username <name> and password <password>
    Then  Verify "Incorrect email or password." message is displayed

    Examples:
      | name          | password |
      | yav@gmail.com | abcDE3   |