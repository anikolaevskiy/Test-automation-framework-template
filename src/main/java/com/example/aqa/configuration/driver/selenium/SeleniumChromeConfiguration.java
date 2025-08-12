package com.example.aqa.configuration.driver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Spring configuration wiring Chrome {@link WebDriver} instances.
 * <p>
 * The configuration becomes active when both {@code selenium} and
 * {@code chrome} profiles are enabled. Additional profiles {@code
 * chrome-local} or {@code chrome-remote} decide whether the browser runs on
 * the local machine or connects to a remote Selenium Grid.
 */
@Profile({"selenium", "chrome"})
@Configuration
@EnableConfigurationProperties(SeleniumProperties.class)
public class SeleniumChromeConfiguration {

    /**
     * Provides default {@link ChromeOptions} for all Chrome sessions.
     *
     * @return configured Chrome options
     */
    @Bean
    public ChromeOptions chromeOptions() {
        return new ChromeOptions().addArguments("--disable-search-engine-choice-screen");
    }

    /**
     * Creates a {@link WebDriver} backed by a remote Selenium Grid.
     * <p>
     * Once instantiated the driver automatically navigates to the application
     * start page defined in {@link SeleniumProperties}.
     *
     * @param properties selenium connection properties
     * @param options    browser options
     * @return remote Chrome driver
     * @throws MalformedURLException if the grid URL is malformed
     * @throws URISyntaxException    if the grid URI cannot be constructed
     */
    @Profile("chrome-remote")
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
     * Creates a local {@link ChromeDriver} instance.
     *
     * @param properties selenium connection properties
     * @param options    browser options
     * @return local Chrome driver
     */
    @Profile("chrome-local")
    @Bean(destroyMethod = "quit")
    public WebDriver localChromeDriver(SeleniumProperties properties, ChromeOptions options) {
        var driver = new ChromeDriver(options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    /**
     * Navigates the given driver to the configured application start page.
     *
     * @param properties selenium connection properties
     * @param driver     driver to navigate
     */
    private static void navigateAppStartPage(SeleniumProperties properties, WebDriver driver) {
        if (properties.getAppPort() != null) {
            driver.navigate().to(String.format("%s:%d", properties.getAppHost(), properties.getAppPort()));
        } else {
            driver.navigate().to(properties.getAppHost());
        }
    }
}
