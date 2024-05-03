@tag
Feature: Purchase order from ecommerce site
  I want to use this template for my feature file

Background:
Given I landed on ecommerce website
  @Regression
  Scenario Outline: positive test of submitting order
    Given logged in with username <name> and password <password>
    When Add product <ProductName> to cart
    And checkout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name  | password | ProductName  |
      | vkharage@gmail.com |Varsha#1 | ZARA COAT 3 |
      | anshika@gmail.com |Iamking@000 | ADIDAS ORIGINAL    |
