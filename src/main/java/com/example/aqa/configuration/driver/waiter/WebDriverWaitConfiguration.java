package com.example.aqa.configuration.driver.waiter;

import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumChromeConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumFireFoxConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;

/**
 * Spring configuration for constructing {@link WebDriverWait} instances.
 * <p>
 * The timeout duration is externalised in {@link WebDriverWaitProperties} so it
 * can be tuned without modifying source code.
 */
@Configuration
@Import({
        AppiumConfiguration.class,
        SeleniumChromeConfiguration.class,
        SeleniumFireFoxConfiguration.class
})
@EnableConfigurationProperties(WebDriverWaitProperties.class)
public class WebDriverWaitConfiguration {

    /**
     * Creates a {@link WebDriverWait} using the default duration from
     * properties.
     *
     * @param properties wait configuration properties
     * @return configured WebDriverWait
     */
    @Bean
    public WebDriverWait webDriverWait(WebDriver webDriver, WebDriverWaitProperties properties) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(properties.getDefaultDuration()));
    }
}
