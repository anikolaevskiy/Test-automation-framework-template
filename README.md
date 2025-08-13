# Java Test Automation Framework Template

This project demonstrates a minimal yet extensible setup for building automated
tests in Java. It combines Spring Boot for dependency management, JUnit 5 for
testing, and optional Appium and Selenium integrations for mobile and web
automation. The framework contains examples of page objects, REST clients,
custom JUnit extensions and configuration classes.

## Prerequisites

- Java 21+
- Maven 3+

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

## Sample Page Objects and Test

The project includes a small demonstration feature composed of the
`ElementsPage` and `UserCard` page objects along with `ExampleTest`. These
classes showcase how to build reusable page components and how to verify
visibility using the new `isDisplayed` method on `AppDriver` implementations.

## Configuration Properties

Several `.properties` files under `src/main/resources` control the behaviour of
drivers and test utilities. Adjust them to match your environment before
running the tests.

### `application.properties`

Defines which Spring profiles are active by default. For example, to use the
local Selenium driver:

```properties
spring.profiles.active=selenium,chrome-local
```

Other combinations are possible depending on the target environment, for
example:

```properties
spring.profiles.active=selenium,chrome-remote  # run against a Selenium Grid
spring.profiles.active=appium                  # execute tests via Appium
spring.profiles.active=playwright,chrome-local # run via Playwright locally
```

You can override this value on the command line with
`-Dspring.profiles.active=...`.

### `selenium.properties`

Settings used when Selenium profiles are active:

- `selenium.app-host` / `selenium.app-port` – address of the web application
  under test.
- `selenium.grid-host` / `selenium.grid-port` – Selenium Grid endpoint when
  running remote browsers.

Example:

```properties
selenium.app-host=localhost
selenium.app-port=1234
selenium.grid-host=http://127.0.0.1
selenium.grid-port=4444
```

### `playwright.properties`

Settings used when Playwright profiles are active:

- `playwright.app-host` / `playwright.app-port` – address of the web
  application under test. Required for both local and remote sessions.
- `playwright.grid-host` / `playwright.grid-port` – remote debugging endpoint
  when connecting to an existing Chrome instance. Omit when running Chrome
  locally.

Example:

```properties
playwright.app-host=localhost
playwright.app-port=1234
playwright.grid-host=http://127.0.0.1
playwright.grid-port=9222
```

### `appium.properties`

Settings used with the `appium` profile:

- `appium.host` and `appium.port` – location of the Appium server.
- `appium.device` – target device name or platform.
- `appium.app` – path to the application under test.
- `appium.time-out` – driver start‑up timeout in seconds.

Example:

```properties
appium.host=http://127.0.0.1
appium.port=4723
appium.device=android
appium.app=/path/to/app.apk
appium.time-out=2
```

### `server.properties`

Configuration for the example REST client:

```properties
server.host=http://127.0.0.1
server.port=1234
```

### `common.properties`

Shared settings for utilities:

- `retry.attempts` – number of retry attempts for flaky operations.
- `retry.backoff` – delay between retry attempts in milliseconds.
- `wait.default-duration` – default explicit wait duration in seconds.

Example:

```properties
retry.attempts=5
retry.backoff=1000
wait.default-duration=30
```

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

To execute browser based tests with Selenium choose the browser and whether it
should run locally or on a Selenium Grid by enabling additional profiles. For
example:

```bash
# run Chrome locally
mvn test -Dspring.profiles.active="selenium,chrome-local"

# run Chrome on a remote Selenium Grid
mvn test -Dspring.profiles.active="selenium,chrome-remote"

# run Firefox locally
mvn test -Dspring.profiles.active="selenium,firefox-local"

# run Firefox on a remote Selenium Grid
mvn test -Dspring.profiles.active="selenium,firefox-remote"
```
The browser automatically navigates to the application host defined in
`src/main/resources/selenium.properties` via the `selenium.app-host` and
optional `selenium.app-port` settings. When running against a Selenium Grid the
grid connection details are read from `selenium.grid-host` and
`selenium.grid-port`.

To execute browser based tests with Playwright choose the browser and whether
it should run locally or connect to an existing Chrome instance. Ensure
`src/main/resources/playwright.properties` points to your application and, for
remote sessions, to the Chrome debugging endpoint:

```bash
# run Chrome locally
mvn test -Dspring.profiles.active="playwright,chrome-local"

# connect to a remote Chrome instance
mvn test -Dspring.profiles.active="playwright,chrome-remote"
```

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

