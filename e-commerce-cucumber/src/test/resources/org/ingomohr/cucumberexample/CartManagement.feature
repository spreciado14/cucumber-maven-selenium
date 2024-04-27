Feature: Cart Management

Scenario Outline: Add multiple items to the cart
  Given the user is logged in and on the product page
  When the user adds <quantity> of <product> to the cart
  Then the cart should contain <quantity> of <product>

Examples:
  | product          | quantity |
  | "Wireless Mouse" | 2        | 
  | "USB Keyboard"   | 1        | 