package com.example.aqa.configuration.driver.selenium;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Spring configuration wiring Selenium WebDriver instances.
 * <p>
 * The configuration becomes active when the {@code selenium} profile is
 * enabled. It imports specific configurations for Chrome and Firefox
 * browsers, allowing users to run tests in different environments.
 */
@Profile({"selenium"})
@Configuration
@Import({
        SeleniumChromeConfiguration.class,
        SeleniumFireFoxConfiguration.class
})
public class SeleniumConfiguration {
}
