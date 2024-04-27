Feature: User Login

Scenario: Successful login with valid credentials
  Given the user is on the login page
  When the user eneters a valid username and password
  And the user clicks the login button
  Then the user should be redirected to the homepage
  And a welcome message should be displayed