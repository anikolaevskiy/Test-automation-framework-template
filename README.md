# Java Test Automation Framework Template

This project demonstrates a minimal yet extensible setup for building automated
tests in Java. It combines Spring Boot for dependency management, JUnit 5 for
testing, and optional Appium and Selenium integrations for mobile and web
automation. The framework contains examples of page objects, REST clients,
custom JUnit extensions and configuration classes.

## Project Structure

```
src/main/java
├── com.example.aqa.app           # Page objects and API client
├── com.example.aqa.configuration # Spring configuration and properties
├── com.example.aqa.driver        # Application driver abstractions
└── com.example.aqa.junit         # JUnit 5 extensions

src/test/java
└── com.example.aqa               # Sample tests using the framework
```

Configuration files for the examples live under `src/main/resources`.

## Prerequisites

- Java 21+
- Maven 3+

## Running Tests

Execute all tests with Maven:

```bash
mvn test
```

The included tests use a mock application driver and a simple REST client. They
can be used as a reference when adding real Appium or web drivers.

### Selecting Spring Profiles

This project uses Spring profiles to switch between different driver
implementations. By default the `mock` profile is active which provides a
light‑weight driver that does not require external infrastructure. To run tests
against a real Appium server activate the `appium` profile:

```bash
mvn test -Dspring.profiles.active=appium
```

Profiles can also be set in `src/main/resources/application.properties`.

To execute browser based tests with Selenium select whether the browser should
run locally or on a Selenium Grid and provide the desired browser profile. For
example:

```bash
# run Chrome locally
mvn test -Dspring.profiles.active="selenium,local,chrome"

# run Firefox on a remote Selenium Grid
mvn test -Dspring.profiles.active="selenium,remote,firefox"
```
The browser automatically navigates to the application host defined in
`src/main/resources/selenium.properties` via the `selenium.app-host` and
optional `selenium.app-port` settings. When running against a Selenium Grid the
grid connection details are read from `selenium.grid-host` and
`selenium.grid-port`.

### Start Writing Your Own Tests

1. **Choose a profile** – keep the default `mock` profile while developing
   framework pieces, then switch to `appium`, `selenium` or another custom
   profile when real infrastructure is available.
2. **Create page objects** – add classes under
   `src/main/java/com/example/aqa/app/client` returning `AppObject` instances for
   the screens you want to exercise.
3. **Provide a driver** – implement the `AppDriver` interface or extend the
   supplied ones to interact with your application. Register the implementation
   in a configuration class and guard it with an appropriate Spring profile.
4. **Write tests** – place your JUnit 5 tests in
   `src/test/java/com/example/aqa` and inject the required page objects or
   clients. Use the `RetryTemplate` bean when verifying asynchronous behaviour.

Following this pattern keeps tests technology‑agnostic and makes switching
between environments as simple as changing the active profile.

## Extending the Template

- Implement your own `AppDriver` to integrate with Appium, Selenium or another
  automation tool.
- Enable the beans in `AppiumConfiguration` or `SeleniumConfiguration` to
  connect to a real device or browser.
- Add additional page objects, API clients or JUnit extensions as needed.

## License

This project is provided as-is without any specific license. Use it as a
starting point for your own testing framework.

