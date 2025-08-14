# Test Automation Framework Template

A simple yet flexible starting point for building UI and API tests in Java.  
The project uses **Spring Boot** for dependency injection, **JUnit 5** for
testing and provides ready‑to‑use integrations for Selenium, Playwright and
Appium.  Each component is deliberately small so newcomers can understand how a
real automation framework is put together and extend it to suit their needs.

---

## 1. Requirements

- Java 21+
- Maven 3+

---

## 2. Project Layout

```
src/main/java
├── com.example.aqa.app           # Page objects and REST client
├── com.example.aqa.configuration # Spring configuration and properties
├── com.example.aqa.driver        # Driver abstractions and implementations
└── com.example.aqa.junit         # JUnit extensions

src/test/java/com.example.aqa     # Example tests
```

Configuration files are stored under `src/main/resources`.

---

## 3. Configuration

The framework uses **Spring profiles** and property files to switch between
execution environments.

### application.properties
Defines the active profiles. Examples:

```properties
spring.profiles.active=selenium,chrome,local    # run Chrome locally
spring.profiles.active=selenium,chrome,remote   # use Selenium Grid
spring.profiles.active=appium                   # run via Appium
spring.profiles.active=playwright,chrome,local  # run via Playwright
```

Override on the command line:

```bash
mvn test -Dspring.profiles.active="selenium,chrome,remote"
```

### Driver‑specific properties
`selenium.properties`, `playwright.properties` and `appium.properties` contain
driver settings such as application host, grid URL or device name.

Example (`selenium.properties`):

```properties
selenium.app-host=https://letcode.in/elements
selenium.grid-host=http://127.0.0.1:4444/wd/hub
```

`common.properties` defines shared utilities like retry counts and default wait
durations.

`server.properties` defines the target API server for REST requests:

```properties
server.host=https://restful-booker.herokuapp.com
```

---

## 4. Writing Tests

1. **Create page objects** under
   `src/main/java/com/example/aqa/app/client`.  A page object usually exposes
   methods that return `AppObject` instances – lightweight wrappers around a
   locator and the `AppDriver`.

   ```java
   @Component
   public class LoginPage {
       private final AppDriver driver;
       public LoginPage(AppDriver driver) { this.driver = driver; }
       public AppObject username() { return new AppObject(driver, "//input[@id='user']"); }
       public AppObject submit()   { return new AppObject(driver, "//button[@id='login']"); }
   }
   ```

2. **Create a test class** in `src/test/java/com/example/aqa` and extend
   `BaseTest` which wires the Spring context, REST client and retry utilities.

   ```java
   class LoginTest extends BaseTest {
       @Autowired private LoginPage loginPage;

       @Test
       void userCanLogIn() {
           loginPage.username().sendText("demo");
           loginPage.submit().click();
           // assertions...
       }
   }
   ```

3. **Run the tests** with the desired profile:

   ```bash
   mvn test                                         # uses built-in mock driver
   mvn test -Dspring.profiles.active="selenium,chrome,local"
   mvn test -Dspring.profiles.active="appium"
   ```

---

## 5. REST API Requests

1. **Configure the host** – update `src/main/resources/server.properties` with
   the address of your API server:

   ```properties
   server.host=https://restful-booker.herokuapp.com
   ```

2. **Add new endpoints** – declare a method in `RestApiClient`, add the HTTP
   mapping in `ServerFeignClient` and delegate to it from
   `FeignRestApiClient`.

3. **Call the API in tests** – `BaseTest` exposes an injected
   `restApiClient`:

   ```java
   @Test
   void createsEntity() {
       var token = restApiClient.auth("user", "pass");
       var entity = restApiClient.createSomething("text", 1);
       // assertions...
   }
   ```

---

## 6. Extending the Framework

- **Add a new driver:** implement the `AppDriver` interface and register it in a
  `@Configuration` class guarded by a Spring profile.
- **Introduce more page objects or REST clients:** place them under the
  `com.example.aqa.app` package and inject them into tests.
- **Create custom JUnit extensions:** add them in the `com.example.aqa.junit`
  package and include with `@ExtendWith` or `@RegisterExtension`.
- **Reuse utilities:** the `RetryTemplate` bean from `CommonConfiguration` helps
  with polling or flaky assertions.

This modular approach keeps tests technology‑agnostic while allowing teams to
plug in the tools that suit their environment.

---

## 7. Allure Reports

All tests output Allure results to `target/allure-results`. Build a static
report:

```bash
mvn allure:report
```

Or generate and open it in a temporary server:

```bash
mvn allure:serve
```

---

## 8. Useful Commands

```bash
mvn -q clean                                    # remove build outputs
mvn test                                        # run tests with mock driver
mvn test -Dspring.profiles.active="selenium,chrome,local"  # use Selenium
mvn allure:serve                                # generate and open Allure report
```

---

## 9. Purpose

This repository aims to be both a learning resource and a skeleton for real
projects. Start with the mock setup, plug in real drivers when ready and evolve
the code to match your application's needs.

---

## 10. License

This project is provided as-is without any specific license.

