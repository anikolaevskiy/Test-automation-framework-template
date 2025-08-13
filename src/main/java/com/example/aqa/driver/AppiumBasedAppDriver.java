package com.example.aqa.driver;

import io.appium.java_client.AppiumDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Simple example of Appium-based {@link AppDriver} implementation.
 * <p>
 * The methods intentionally delegate directly to the underlying
 * {@link AppiumDriver}. The goal is to keep this layer thin so that teams can
 * expand it with their own waiting strategies or logging while page objects and
 * tests remain unchanged.
 */
@Slf4j
@RequiredArgsConstructor
public class AppiumBasedAppDriver implements AppDriver {

    /** Underlying Appium driver executing real commands. */
    private final WebDriver appiumDriver;

    private final WebDriverWait wait;

    /** {@inheritDoc} */
    @Override
    public void click(String locator) {
        log.info("Clicking on element with locator: {} by appium driver", locator);
        appiumDriver.findElement(By.xpath(locator)).click();
    }

    /** {@inheritDoc} */
    @Override
    public String getText(String locator) {
        log.info("Getting text from element with locator: {} by appium driver", locator);
        return appiumDriver.findElement(By.xpath(locator)).getText();
    }

    /** {@inheritDoc} */
    @Override
    public void sendText(String locator, String text) {
        log.info("Sending text '{}' to element with locator: {} by appium driver", text, locator);
        appiumDriver.findElement(By.xpath(locator)).sendKeys(text);
    }

    /** {@inheritDoc} */
    @Override
    public void waitObject(String locator) {
        log.info("Waiting for element with locator: {} by appium driver", locator);
        wait.until(driver -> driver.findElement(By.xpath(locator)).isDisplayed());
    }
}

