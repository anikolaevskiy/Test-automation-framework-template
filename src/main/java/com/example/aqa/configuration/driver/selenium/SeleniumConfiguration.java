package com.example.aqa.configuration.driver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    public ChromeOptions options() {
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
    public WebDriver webDriver(SeleniumProperties properties, ChromeOptions options) {

        var driver = new ChromeDriver(options);
        if (properties.getHost() != null) {
            driver.navigate().to(String.format("%s:%d", properties.getHost(), properties.getPort()));
        } else {
            driver.navigate().to(properties.getHost());
        }
        return driver;
    }
}
