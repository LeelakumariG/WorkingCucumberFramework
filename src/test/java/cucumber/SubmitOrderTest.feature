@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce Page 

  @Regression
  Scenario Outline: Positive test of the submitting order 
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

Examples: 
  | name                    | password       | productName  |
  | latepractice@example.com | Practice@12345 | ZARA COAT 3  |
    
