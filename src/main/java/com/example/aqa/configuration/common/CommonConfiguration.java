package com.example.aqa.configuration.common;

import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.configuration.driver.playwright.PlaywrightChromiumConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumChromeConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumFireFoxConfiguration;
import com.example.aqa.tools.Screenshot;
import com.microsoft.playwright.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.support.RetryTemplate;

/**
 * Common framework configuration such as retry templates.
 * <p>
 * Centralising cross‑cutting beans avoids repeating wiring in every test and
 * makes it easy to tune behaviour through properties.
 */
@Configuration
@EnableConfigurationProperties(RetryProperties.class)
@Import({AppiumConfiguration.class, SeleniumChromeConfiguration.class, SeleniumFireFoxConfiguration.class, PlaywrightChromiumConfiguration.class})
public class CommonConfiguration {

    /**
     * Creates a {@link RetryTemplate} used for repeating assertions.
     * <p>
     * Retry logic is useful when the system under test exhibits eventual
     * consistency. Exposing the attempts and backoff via {@link RetryProperties}
     * allows the same tests to be tuned for different environments.
     *
     * @param properties retry configuration properties
     * @return configured retry template
     */
    @Bean
    public RetryTemplate assertionRetry(RetryProperties properties) {
        return RetryTemplate.builder().retryOn(AssertionError.class)
                .maxAttempts(properties.getAttempts())
                .fixedBackoff(properties.getBackoff())
                .build();
    }
    @Bean
    @Profile("selenium")
    public Screenshot seleniumScreenshot(WebDriver webDriver) {
        return () -> ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @Bean
    @Profile("appium")
    public Screenshot appiumScreenshot(AppiumDriver appiumDriver) {
        return () -> appiumDriver.getScreenshotAs(OutputType.BYTES);
    }

    @Bean
    @Profile("playwright")
    public Screenshot playwrightScreenshot(Page page) {
        return page::screenshot;
    }

    @Bean
    @Profile("!selenium && !appium && !playwright")
    public Screenshot mockScreenshot() {
        return () -> new byte[0]; // Mock screenshot returns empty byte array
    }
}
