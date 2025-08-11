package com.example.aqa.driver;

import io.appium.java_client.AppiumDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

/**
 * Simple example of Appium-based {@link AppDriver} implementation.
 */
@Slf4j
@RequiredArgsConstructor
public class AppiumBasedAppDriver implements AppDriver {

    private final AppiumDriver appiumDriver;

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
        // Implement waiting logic here, e.g., using WebDriverWait
        // Example: new WebDriverWait(appiumDriver, Duration.ofSeconds(10))
        //      .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        // Note: Actual implementation may vary based on your requirements and Appium setup.
    }
}

