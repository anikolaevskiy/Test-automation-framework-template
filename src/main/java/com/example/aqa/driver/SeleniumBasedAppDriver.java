package com.example.aqa.driver;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * {@link AppDriver} implementation backed by Selenium's {@link WebDriver}.
 * <p>
 * The implementation deliberately mirrors {@link AppiumBasedAppDriver} to keep the
 * examples consistent across different technologies. Tests interact with the
 * {@link AppDriver} interface while this class handles low level WebDriver
 * calls.
 */
@Slf4j
@RequiredArgsConstructor
public class SeleniumBasedAppDriver implements AppDriver {

    /** Underlying Selenium driver executing commands in a browser. */
    private final WebDriver webDriver;

    private final WebDriverWait wait;

    /** {@inheritDoc} */
    @Override
    @Step("Click element located by {locator}")
    public void click(String locator) {
        log.info("Clicking on element with locator: {} by selenium driver", locator);
        webDriver.findElement(By.xpath(locator)).click();
    }

    /** {@inheritDoc} */
    @Override
    @Step("Get text from element located by {locator}")
    public String getText(String locator) {
        log.info("Getting text from element with locator: {} by selenium driver", locator);
        return webDriver.findElement(By.xpath(locator)).getText();
    }

    /** {@inheritDoc} */
    @Override
    @Step("Send text {text} to element located by {locator}")
    public void sendText(String locator, String text) {
        log.info("Sending text '{}' to element with locator: {} by selenium driver", text, locator);
        webDriver.findElement(By.xpath(locator)).sendKeys(text);
    }

    /** {@inheritDoc} */
    @Override
    @Step("Check if element located by {locator} is displayed")
    public boolean isDisplayed(String locator) {
        return webDriver.findElement(By.xpath(locator)).isDisplayed();
    }

    /** {@inheritDoc} */
    @Override
    @Step("Wait for element located by {locator}")
    public void waitObject(String locator) {
        log.info("Waiting for element with locator: {} by selenium driver", locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
}
