package com.example.aqa.configuration.driver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration that wires a {@link WebDriver} when the {@code selenium} profile is active.
 * <p>
 * Keeping Selenium-specific beans behind a profile allows the rest of the
 * framework to run without a browser. Once enabled this configuration creates
 * a {@link WebDriver} either locally.
 */
@Profile("selenium")
@Configuration
@EnableConfigurationProperties(SeleniumProperties.class)
public class SeleniumConfiguration {


    @Profile("chrome")
    @Bean
    public ChromeOptions chromeOptions() {
        return new ChromeOptions().addArguments("--disable-search-engine-choice-screen");
    }

    /**
     * Creates the {@link WebDriver} instance.
     * <p>
     * Local {@link ChromeDriver}
     *
     * @param properties Selenium configuration properties
     * @return configured WebDriver
     */
    @Profile("chrome")
    @Bean(destroyMethod = "quit")
    public WebDriver chromeDriver(SeleniumProperties properties, ChromeOptions options) {
        var driver = new ChromeDriver(options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    /**
     * Creates the {@link WebDriver} instance.
     * <p>
     * Local {@link FirefoxDriver}
     *
     * @param properties Selenium configuration properties
     * @return configured WebDriver
     */
    @Profile("firefox")
    @Bean(destroyMethod = "quit")
    public WebDriver firefox(SeleniumProperties properties) {
        var driver = new FirefoxDriver();
        navigateAppStartPage(properties, driver);
        return driver;
    }

    private static void navigateAppStartPage(SeleniumProperties properties, WebDriver driver) {
        if (properties.getAppPort() != null) {
            driver.navigate().to(String.format("%s:%d", properties.getAppHost(), properties.getAppPort()));
        } else {
            driver.navigate().to(properties.getAppHost());
        }
    }
}
