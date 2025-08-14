package com.example.aqa.configuration.driver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Spring configuration wiring Firefox {@link WebDriver} instances.
 * <p>
 * The configuration requires the {@code selenium} and {@code firefox}
 * profiles. Additional {@code firefox-local} or {@code firefox-remote}
 * profiles decide whether the browser runs on the current machine or connects
 * to a Selenium Grid.
 */
@Profile({"selenium"})
@Configuration
@EnableConfigurationProperties(SeleniumProperties.class)
public class SeleniumFireFoxConfiguration {

    /**
     * Provides default {@link FirefoxOptions} for all Firefox sessions.
     *
     * @return configured Firefox options
     */
    @Bean
    public FirefoxOptions firefoxOptions() {
        return new FirefoxOptions();
    }

    /**
     * Creates a {@link WebDriver} that connects to a remote Selenium Grid.
     *
     * @param properties selenium connection properties
     * @param options    browser options
     * @return remote Firefox driver
     * @throws MalformedURLException if the grid URL is malformed
     * @throws URISyntaxException    if the grid URI cannot be constructed
     */
    @Profile("firefox && remote")
    @Bean(destroyMethod = "quit")
    public WebDriver remoteFirefoxDriver(SeleniumProperties properties, FirefoxOptions options) throws MalformedURLException, URISyntaxException {
        var driver = new RemoteWebDriver(new URI(properties.getGridHost()).toURL(), options);
        driver.navigate().to(properties.getAppHost());
        return driver;
    }

    /**
     * Creates a local {@link FirefoxDriver} instance.
     *
     * @param properties selenium connection properties
     * @param options    browser options
     * @return local Firefox driver
     */
    @Profile("firefox && local")
    @Bean(destroyMethod = "quit")
    public WebDriver localFirefoxDriver(SeleniumProperties properties, FirefoxOptions options) {
        var driver = new FirefoxDriver(options);
        driver.navigate().to(properties.getAppHost());
        return driver;
    }
}
