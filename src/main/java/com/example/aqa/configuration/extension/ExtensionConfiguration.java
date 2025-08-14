package com.example.aqa.configuration.extension;

import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.configuration.driver.playwright.PlaywrightChromiumConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumChromeConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumFireFoxConfiguration;
import com.example.aqa.junit.extension.ScreenshotOnFailureExtension;
import com.microsoft.playwright.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Spring configuration that registers JUnit extensions as beans.
 * <p>
 * Declaring extensions as beans allows them to participate in dependency
 * injection and be shared across tests, keeping setup logic in one place.
 */
@Configuration
@Import({AppiumConfiguration.class, SeleniumChromeConfiguration.class, SeleniumFireFoxConfiguration.class, PlaywrightChromiumConfiguration.class})
public class ExtensionConfiguration {

    /**
     * Provides an extension that captures screenshots upon test failures.
     *
     * @return instance of {@link ScreenshotOnFailureExtension}
     */

    @Bean
    @Profile("playwright")
    public ScreenshotOnFailureExtension playwrightScreenshotOnFailureExtension(Page page) {
        return new ScreenshotOnFailureExtension(page::screenshot);
    }

    @Bean
    @Profile("selenium")
    public ScreenshotOnFailureExtension selenumScreenshotOnFailureExtension(WebDriver webDriver) {
        return new ScreenshotOnFailureExtension(() -> ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
    }

    @Bean
    @Profile("appium")
    public ScreenshotOnFailureExtension appiumScreenshotOnFailureExtension(AppiumDriver appiumDriver) {
        return new ScreenshotOnFailureExtension(() -> appiumDriver.getScreenshotAs(OutputType.BYTES));
    }

    @Bean
    @Profile("!playwright && !selenium && !appium")
    public ScreenshotOnFailureExtension screenshotOnFailureExtension() {
        return new ScreenshotOnFailureExtension(() -> new byte[0]);
    }
}
