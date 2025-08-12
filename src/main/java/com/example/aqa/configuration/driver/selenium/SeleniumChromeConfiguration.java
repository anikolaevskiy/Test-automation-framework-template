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

@Profile({"selenium", "chrome"})
@Configuration
@EnableConfigurationProperties(SeleniumProperties.class)
public class SeleniumChromeConfiguration {

    @Bean
    public ChromeOptions chromeOptions() {
        return new ChromeOptions().addArguments("--disable-search-engine-choice-screen");
    }

    @Profile("chrome-remote")
    @Bean(destroyMethod = "quit")
    public WebDriver remoteChromeDriver(SeleniumProperties properties, ChromeOptions options) throws MalformedURLException, URISyntaxException {
        var driver = new RemoteWebDriver(new URI(String.format("%s:%d/wd/hub", properties.getGridHost(), properties.getGridPort())).toURL(), options);
        navigateAppStartPage(properties, driver);
        return driver;
    }

    @Profile("chrome-local")
    @Bean(destroyMethod = "quit")
    public WebDriver localChromeDriver(SeleniumProperties properties, ChromeOptions options) {
        var driver = new ChromeDriver(options);
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
