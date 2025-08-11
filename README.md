# Java Test Automation Framework Template

This project demonstrates a minimal yet extensible setup for building automated
tests in Java. It combines Spring Boot for dependency management, JUnit 5 for
testing, and optional Appium integration for mobile automation. The framework
contains examples of page objects, REST clients, custom JUnit extensions and
configuration classes.

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

## Extending the Template

- Implement your own `AppDriver` to integrate with Appium, Selenium or another
  automation tool.
- Enable the beans in `AppiumConfiguration` to connect to a real Appium server.
- Add additional page objects, API clients or JUnit extensions as needed.

## License

This project is provided as-is without any specific license. Use it as a
starting point for your own testing framework.

