package com.example.aqa.configuration.driver.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * Placeholder configuration for an Appium-based driver.
 * Uncomment the example beans to enable real Appium support.
 */
@Profile("appium")
@Configuration
@EnableConfigurationProperties(AppiumProperties.class)
public class AppiumConfiguration {

    @Bean
    public Capabilities capabilities(AppiumProperties properties) {
        return new UiAutomator2Options()
                .setNewCommandTimeout(Duration.ofMinutes(properties.getTimeOut()))
                .setDeviceName(properties.getDevice())
                .setApp(properties.getApp());
    }

    @Bean(destroyMethod = "quit")
    public AppiumDriver appiumDriver(Capabilities capabilities, AppiumProperties properties) throws MalformedURLException, URISyntaxException {
        return new AppiumDriver(new URI(String.format("%s:%d", properties.getHost(), properties.getPort())).toURL(), capabilities);
    }
}
