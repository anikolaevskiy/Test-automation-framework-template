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
import java.net.URL;

/**
 * Configuration that wires a {@link WebDriver} when the {@code selenium} profile is active.
 * <p>
 * Keeping Selenium-specific beans behind a profile allows the rest of the
 * framework to run without a browser. Once enabled this configuration creates
 * a {@link WebDriver} either locally or by connecting to a remote Selenium server.
 */
@Profile("selenium")
@Configuration
@EnableConfigurationProperties(SeleniumProperties.class)
public class SeleniumConfiguration {

    /**
     * Creates the {@link WebDriver} instance.
     * <p>
     * If remote host and port are provided, the driver connects to that Selenium Grid,
     * otherwise a local {@link ChromeDriver} is started.
     *
     * @param properties Selenium configuration properties
     * @return configured WebDriver
     * @throws URISyntaxException    if the remote URI cannot be constructed
     * @throws MalformedURLException if the remote URL is invalid
     */
    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(SeleniumProperties properties) throws URISyntaxException, MalformedURLException {
        if (properties.getRemoteHost() != null && !properties.getRemoteHost().isBlank()) {
            URI remoteUri = new URI("http", null, properties.getRemoteHost(), properties.getRemotePort(), null, null, null);
            return new RemoteWebDriver(remoteUri.toURL(), new ChromeOptions());
        }
        return new ChromeDriver();
    }
}
