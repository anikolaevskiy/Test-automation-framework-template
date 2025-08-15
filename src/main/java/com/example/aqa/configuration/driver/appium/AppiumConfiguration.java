package com.example.aqa.configuration.driver.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * Configuration that wires an {@link AppiumDriver} when the {@code appium}
 * profile is active.
 * <p>
 * Keeping Appium specific beans behind a profile allows the rest of the
 * framework to remain lightweight and runnable without a device. Once the
 * profile is enabled, the beans defined here use externalized properties to
 * build {@link UiAutomator2Options} and connect to the target Appium server.
 * This design demonstrates how users can plug in their real capabilities
 * without modifying framework code.
 */
@Profile("appium")
@Configuration
@EnableConfigurationProperties(AppiumProperties.class)
public class AppiumConfiguration {

    /**
     * Builds the desired capabilities for Appium from external properties.
     * <p>
     * Using {@link UiAutomator2Options} here keeps the example focused on
     * Android, but the same pattern can be reused for any supported platform by
     * replacing the options class. Externalizing the values into
     * {@link AppiumProperties} means switching devices or apps does not require
     * recompiling tests.
     *
     * @param properties values loaded from {@code appium.properties}
     * @return configured capabilities for the session
     */
    @Bean
    public Capabilities capabilities(AppiumProperties properties) {
        return new UiAutomator2Options()
                .setNewCommandTimeout(Duration.ofMinutes(properties.timeOut()))
                .setDeviceName(properties.device())
                .setApp(properties.app());
    }

    /**
     * Creates the {@link AppiumDriver} instance.
     * <p>
     * The driver is configured to quit automatically when the Spring context is
     * closed which keeps test runs tidy. Connecting via properties allows
     * the same compiled tests to run against different Appium servers.
     *
     * @param capabilities previously constructed capabilities
     * @param properties connection details for the Appium server
     * @return new Appium driver ready for use in tests
     * @throws MalformedURLException if the server URL is invalid
     * @throws URISyntaxException    if the server URI cannot be constructed
     */
    @Bean(destroyMethod = "quit")
    public AppiumDriver appiumDriver(Capabilities capabilities, AppiumProperties properties) throws MalformedURLException, URISyntaxException {
        return new AppiumDriver(
                new URI(properties.host()).toURL(),
                capabilities);
    }
}
