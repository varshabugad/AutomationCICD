
@tag
Feature: Error Message validation
  I want to use this template for my feature file



  @ErrorValidations
  Scenario Outline: Error Message validation
    Given I landed on ecommerce website
    When logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 
    
       Examples: 
      | name               | password |
      | vkhsarage@gmail.com |Varsha#1  |
  