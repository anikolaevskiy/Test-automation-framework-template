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

@Profile({"selenium", "firefox"})
@Configuration
@EnableConfigurationProperties(SeleniumProperties.class)
public class SeleniumFireFoxConfiguration {

    @Bean
    public FirefoxOptions firefoxOptions() {
        return new FirefoxOptions();
    }

    @Profile("firefox-remote")
    @Bean(destroyMethod = "quit")
    public WebDriver remoteFirefoxDriver(SeleniumProperties properties, FirefoxOptions options) throws MalformedURLException, URISyntaxException {
        var driver = new RemoteWebDriver(new URI(String.format("%s:%d/wd/hub", properties.getGridHost(), properties.getGridPort())).toURL(), options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    @Profile("firefox-local")
    @Bean(destroyMethod = "quit")
    public WebDriver localFirefoxDriver(SeleniumProperties properties, FirefoxOptions options) {
        var driver = new FirefoxDriver(options);
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
