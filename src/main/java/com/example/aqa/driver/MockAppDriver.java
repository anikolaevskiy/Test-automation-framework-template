package com.example.aqa.driver;

import com.example.aqa.driver.core.AppDriver;
import com.example.aqa.driver.core.ElementInteraction;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

/**
 * Mock implementation of {@link ElementInteraction} for testing purposes.
 * <p>
 * The mock driver is intentionally verbose and side‑effect free so the rest of
 * the framework can be exercised without connecting to a real application.
 * Teams are expected to replace this with their own implementation (Appium,
 * Selenium, Playwright, etc.) but keeping a mock version makes the template
 * runnable out of the box.
 */
@Slf4j
public class MockAppDriver extends AppDriver {

    /** {@inheritDoc} */
    @Override
    @Step("Click element located by {locator}")
    public void click(String locator) {
        log.info("Mock click on locator: {}", locator);
    }

    /** {@inheritDoc} */
    @Override
    @Step("Get text from element located by {locator}")
    public String getText(String locator) {
        log.info("Mock getText on locator: {}", locator);
        return "Mock text value";
    }

    /** {@inheritDoc} */
    @Override
    @Step("Send text {text} to element located by {locator}")
    public void sendText(String locator, String text) {
        log.info("Mock sendText on locator: {}, text: {}", locator, text);
    }

    /** {@inheritDoc} */
    @Override
    @Step("Check if element located by {locator} is displayed")
    public boolean isDisplayed(String locator) {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    @Step("Wait for element located by {locator}")
    public void waitObject(String locator) {
        log.info("Mock waitObject on locator: {}", locator);
    }

    @Override
    public byte[] takeScreenshot() {
        return new byte[0];
    }
}
