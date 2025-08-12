package com.example.aqa.configuration.driver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Selenium driver configuration that exposes a local {@link WebDriver} when
 * the {@code selenium} profile is active.
 * <p>
 * To select a concrete browser activate an additional profile. For example,
 * run tests with {@code -Dspring.profiles.active=selenium,chrome} to control
 * Google Chrome or replace {@code chrome} with {@code firefox} to start
 * Mozilla Firefox. Keeping browsers behind profiles lets the framework stay
 * lightweight while still allowing browser-specific execution.
 */
@Profile("selenium")
@Configuration
public class SeleniumConfiguration {

    /**
     * Creates a {@link ChromeDriver} when the {@code chrome} profile is active.
     *
     * @return WebDriver controlling Google Chrome
     */
    @Bean(destroyMethod = "quit")
    @Profile("chrome")
    public WebDriver chromeDriver() {
        return new ChromeDriver();
    }

    /**
     * Creates a {@link FirefoxDriver} when the {@code firefox} profile is active.
     *
     * @return WebDriver controlling Mozilla Firefox
     */
    @Bean(destroyMethod = "quit")
    @Profile("firefox")
    public WebDriver firefoxDriver() {
        return new FirefoxDriver();
    }
}
