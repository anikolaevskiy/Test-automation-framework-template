package com.example.aqa.configuration.driver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration that wires a {@link WebDriver} when the {@code selenium}
 * profile is active.
 * <p>
 * Selenium-specific beans are isolated behind profiles so the framework can
 * run without a browser. When the {@code selenium} profile is combined with a
 * browser profile such as {@code chrome} or {@code firefox}, this
 * configuration creates a local {@link WebDriver} and navigates to the
 * application start page defined in {@link SeleniumProperties}.
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

    @Profile("firefox")
    @Bean
    public FirefoxOptions firefoxOptions() {
        return new FirefoxOptions();
    }

    /**
     * Creates a local {@link ChromeDriver} and opens the application start page.
     *
     * @param properties Selenium configuration properties
     * @param options    additional Chrome options
     * @return configured WebDriver
     */
    @Profile({"chrome", "local"})
    @Bean(destroyMethod = "quit")
    public WebDriver localChromeDriver(SeleniumProperties properties, ChromeOptions options) {
        var driver = new ChromeDriver(options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    /**
     * Creates a remote {@link WebDriver} session on Selenium Grid using Chrome
     * and opens the application start page.
     *
     * @param properties Selenium configuration properties
     * @param options    additional Chrome options
     * @return configured WebDriver
     * @throws MalformedURLException if the grid URL is invalid
     * @throws URISyntaxException if the grid URL is invalid
     */
    @Profile({"chrome", "remote"})
    @Bean(destroyMethod = "quit")
    public WebDriver remoteChromeDriver(SeleniumProperties properties, ChromeOptions options)
            throws MalformedURLException, URISyntaxException {
        var driver = new RemoteWebDriver(
                new URI(String.format("%s:%d/wd/hub", properties.getGridHost(), properties.getGridPort())).toURL(),
                options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    /**
     * Creates a local {@link FirefoxDriver} and opens the application start page.
     *
     * @param properties Selenium configuration properties
     * @param options    additional Firefox options
     * @return configured WebDriver
     */
    @Profile({"firefox", "local"})
    @Bean(destroyMethod = "quit")
    public WebDriver localFirefoxDriver(SeleniumProperties properties, FirefoxOptions options) {
        var driver = new FirefoxDriver(options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    /**
     * Creates a remote {@link WebDriver} session on Selenium Grid using Firefox
     * and opens the application start page.
     *
     * @param properties Selenium configuration properties
     * @param options    additional Firefox options
     * @return configured WebDriver
     * @throws MalformedURLException if the grid URL is invalid
     * @throws URISyntaxException if the grid URL is invalid
     */
    @Profile({"firefox", "remote"})
    @Bean(destroyMethod = "quit")
    public WebDriver remoteFirefoxDriver(SeleniumProperties properties, FirefoxOptions options)
            throws MalformedURLException, URISyntaxException {
        var driver = new RemoteWebDriver(
                new URI(String.format("%s:%d/wd/hub", properties.getGridHost(), properties.getGridPort())).toURL(),
                options);
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
