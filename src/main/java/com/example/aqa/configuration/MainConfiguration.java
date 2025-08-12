package com.example.aqa.configuration;

import com.example.aqa.configuration.common.CommonConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumChromeConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumFireFoxConfiguration;
import com.example.aqa.configuration.driver.waiter.WebDriverWaitConfiguration;
import com.example.aqa.configuration.extension.ExtensionConfiguration;
import com.example.aqa.configuration.rest.RestApiClientConfiguration;
import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.driver.AppDriver;
import com.example.aqa.driver.AppiumBasedAppDriver;
import com.example.aqa.driver.MockAppDriver;
import com.example.aqa.driver.SeleniumBasedAppDriver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * Entry point Spring configuration assembling all framework pieces.
 * <p>
 * The template relies heavily on Spring profiles to keep different
 * technologies isolated. By defining beans behind profiles we can switch
 * between a fast mock implementation and a real Appium backed driver
 * without touching test code or recompiling the project. This class wires
 * together all optional parts so that users can enable only what they need
 * via <code>spring.profiles.active</code> property.
 */
@Configuration
@ComponentScan(basePackages = "com.example.aqa")
@Import({
        AppiumConfiguration.class,
        SeleniumChromeConfiguration.class,
        SeleniumFireFoxConfiguration.class,
        WebDriverWaitConfiguration.class,
        RestApiClientConfiguration.class,
        ExtensionConfiguration.class,
        CommonConfiguration.class
})
public class MainConfiguration {

    /**
     * Provides the {@link AppDriver} used in tests.
     * <p>
     * The mock driver is activated by the {@code mock} profile and is intended
     * for quick feedback during framework development or when no real
     * application is available. Keeping it behind a profile allows the same
     * tests to be executed in a lightweight mode without external
     * dependencies.
     *
     * @return mock implementation of the application driver
     */
    @Bean
    @Profile("mock")
    public AppDriver mockAppDriver() {
        return new MockAppDriver();
    }

    /**
     * Provides the {@link AppDriver} used in tests when running with Appium.
     * <p>
     * Beans in the {@code appium} profile are only created when a real device
     * or simulator is available. Separating it from the mock driver keeps the
     * default configuration lightweight while still allowing an easy switch to
     * full end‑to‑end tests.
     *
     * @param appiumDriver the Appium driver instance
     * @return Appium-based implementation of the application driver
     */
    @Bean
    @Profile("appium")
    public AppDriver appiumBasedAppDriver(AppiumDriver appiumDriver, WebDriverWait webDriverWait) {
        return new AppiumBasedAppDriver(appiumDriver, webDriverWait);
    }

    /**
     * Provides the {@link AppDriver} used in tests when running with Selenium.
     * <p>
     * Activating the {@code selenium} profile enables browser based testing
     * without altering test code. This bean adapts the raw {@link WebDriver}
     * into the framework's {@link AppDriver} abstraction.
     *
     * @param webDriver the Selenium driver instance
     * @return Selenium-based implementation of the application driver
     */
    @Bean
    @Profile("selenium")
    public AppDriver seleniumBasedAppDriver(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new SeleniumBasedAppDriver(webDriver, webDriverWait);
    }

}
