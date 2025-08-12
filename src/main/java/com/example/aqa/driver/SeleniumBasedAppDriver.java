package com.example.aqa.driver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * {@link AppDriver} implementation backed by Selenium's {@link WebDriver}.
 * <p>
 * The implementation deliberately mirrors {@link AppiumBasedAppDriver} to keep the
 * examples consistent across different technologies. Tests interact with the
 * {@link AppDriver} interface while this class handles low level WebDriver
 * calls. The concrete browser driver (Chrome or Firefox) is supplied by Spring
 * based on the active profile.
 */
@Slf4j
@RequiredArgsConstructor
public class SeleniumBasedAppDriver implements AppDriver {

    /** Underlying Selenium driver executing commands in a browser. */
    private final WebDriver webDriver;

    /** {@inheritDoc} */
    @Override
    public void click(String locator) {
        log.info("Clicking on element with locator: {} by selenium driver", locator);
        webDriver.findElement(By.xpath(locator)).click();
    }

    /** {@inheritDoc} */
    @Override
    public String getText(String locator) {
        log.info("Getting text from element with locator: {} by selenium driver", locator);
        return webDriver.findElement(By.xpath(locator)).getText();
    }

    /** {@inheritDoc} */
    @Override
    public void sendText(String locator, String text) {
        log.info("Sending text '{}' to element with locator: {} by selenium driver", text, locator);
        webDriver.findElement(By.xpath(locator)).sendKeys(text);
    }

    /** {@inheritDoc} */
    @Override
    public void waitObject(String locator) {
        log.info("Waiting for element with locator: {} by selenium driver", locator);
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
}
