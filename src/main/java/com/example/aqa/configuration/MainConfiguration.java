package com.example.aqa.configuration;

import com.example.aqa.configuration.app.AppDriverConfiguration;
import com.example.aqa.configuration.common.CommonConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumChromeConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumConfiguration;
import com.example.aqa.configuration.driver.selenium.SeleniumFireFoxConfiguration;
import com.example.aqa.configuration.driver.playwright.PlaywrightChromiumConfiguration;
import com.example.aqa.configuration.driver.waiter.WebDriverWaitConfiguration;
import com.example.aqa.configuration.extension.ExtensionConfiguration;
import com.example.aqa.configuration.rest.RestApiClientConfiguration;
import com.example.aqa.configuration.driver.appium.AppiumConfiguration;
import com.example.aqa.driver.core.AppDriver;
import com.example.aqa.driver.core.ElementInteraction;
import com.example.aqa.driver.AppiumBasedAppDriver;
import com.example.aqa.driver.MockAppDriver;
import com.example.aqa.driver.SeleniumBasedAppDriver;
import com.example.aqa.driver.PlaywrightBasedAppDriver;
import com.microsoft.playwright.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.*;

/**
 * Entry point Spring configuration assembling all framework pieces.
 * <p>
 * This configuration class is responsible for wiring together all the components needed for the application to function.
 * It includes configurations for different drivers (Appium, Selenium, Playwright), REST API client, and common utilities.
 */
@Configuration
@ComponentScan(basePackages = "com.example.aqa")
@Import({
        AppDriverConfiguration.class,
        AppiumConfiguration.class,
        SeleniumConfiguration.class,
        PlaywrightChromiumConfiguration.class,
        WebDriverWaitConfiguration.class,
        RestApiClientConfiguration.class,
        ExtensionConfiguration.class,
        CommonConfiguration.class
})
public class MainConfiguration {
}
