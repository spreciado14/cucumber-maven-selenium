# Cucumber Example

This is a small example of how to use Cucumber in a Java project (Java 17, JUnit5).
note: This is being done in VSCode thanks to java extension

## Setup

1. Install Java 17 and Maven on your machine.
2. Clone this repository.
3. Navigate to the project directory in your terminal.
4. Run `mvn clean install` to download the necessary dependencies.

## Running the Tests

1. Ensure you have Chrome installed as the tests run on a ChromeDriver.
2. Update the login URL, username, and password in the `StepDefinitions.java` file.
3. Run `mvn test` in your terminal to start the tests.

## Environment Configuration

This project uses the Selenium WebDriver for browser automation. The WebDriver interacts with the Chrome browser, so ensure you have it installed on your machine. The version of the ChromeDriver used should be compatible with the installed Chrome version.

The project is set up to run on Java 17. If you're using a different version, update the `java.version` property in the `pom.xml` file.

The tests are written using the Cucumber framework with JUnit5. The scenarios are defined in the `CartManagement.feature` file, and the step definitions are in the `StepDefinitions.java` file.

## Cross-Browser Testing

For cross-browser testing, ensure you have downloaded both FirefoxDriver and ChromeDriver, as these are necessary for automation.

To switch between browsers during testing, you will need to change the `browserType` variable in your test setup. For example, use `private String browserType = "chrome";` for testing in Chrome, and `private String browserType = "firefox";` for testing in Firefox.
