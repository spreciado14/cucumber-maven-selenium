@ship
Feature: Shipping Details
Scenario: Change Details
  Given the user is logged in and on the cart page
  When the user updates the shipping detail
  Then check if the update is valid on the cart page
