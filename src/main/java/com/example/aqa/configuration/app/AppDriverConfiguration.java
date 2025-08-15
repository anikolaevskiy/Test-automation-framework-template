package com.example.aqa.configuration.app;

import com.example.aqa.driver.AppiumBasedAppDriver;
import com.example.aqa.driver.MockAppDriver;
import com.example.aqa.driver.PlaywrightBasedAppDriver;
import com.example.aqa.driver.SeleniumBasedAppDriver;
import com.example.aqa.driver.core.AppDriver;
import com.example.aqa.driver.core.ElementInteraction;
import com.microsoft.playwright.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppDriverConfiguration {

    /**
     * Provides the {@link ElementInteraction} used in tests.
     * <p>
     * The mock driver is the default implementation used when no specific profile set
     * for quick feedback during framework development or when no real
     * application is available. Keeping it behind a profile allows the same
     * tests to be executed in a lightweight mode without external
     * dependencies.
     *
     * @return mock implementation of the application driver
     */
    @Bean
    @Profile("!appium && !selenium && !playwright")
    public AppDriver mockAppDriver() {
        return new MockAppDriver();
    }

    /**
     * Provides the {@link ElementInteraction} used in tests when running with Appium.
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
     * Provides the {@link ElementInteraction} used in tests when running with Selenium.
     * <p>
     * Activating the {@code selenium} profile enables browser based testing
     * without altering test code. This bean adapts the raw {@link WebDriver}
     * into the framework's {@link ElementInteraction} abstraction.
     *
     * @param webDriver the Selenium driver instance
     * @return Selenium-based implementation of the application driver
     */
    @Bean
    @Profile("selenium")
    public AppDriver seleniumBasedAppDriver(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new SeleniumBasedAppDriver(webDriver, webDriverWait);
    }

    /**
     * Provides the {@link ElementInteraction} used in tests when running with Playwright.
     * <p>
     * Activating the {@code playwright} profile enables browser automation via
     * Playwright without altering test code. This bean adapts the Playwright
     * {@link Page} into the framework's {@link ElementInteraction} abstraction.
     *
     * @param page the Playwright page instance
     * @return Playwright-based implementation of the application driver
     */
    @Bean
    @Profile("playwright")
    public AppDriver playwrightBasedAppDriver(Page page) {
        return new PlaywrightBasedAppDriver(page);
    }
}
