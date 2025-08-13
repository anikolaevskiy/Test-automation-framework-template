package com.example.aqa.driver;

import lombok.extern.slf4j.Slf4j;

/**
 * Mock implementation of {@link AppDriver} for testing purposes.
 * <p>
 * The mock driver is intentionally verbose and side‑effect free so the rest of
 * the framework can be exercised without connecting to a real application.
 * Teams are expected to replace this with their own implementation (Appium,
 * Selenium, Playwright, etc.) but keeping a mock version makes the template
 * runnable out of the box.
 */
@Slf4j
public class MockAppDriver implements AppDriver {

    /** {@inheritDoc} */
    @Override
    public void click(String locator) {
        log.info("Mock click on locator: {}", locator);
    }

    /** {@inheritDoc} */
    @Override
    public String getText(String locator) {
        log.info("Mock getText on locator: {}", locator);
        return "Mock text value";
    }

    /** {@inheritDoc} */
    @Override
    public void sendText(String locator, String text) {
        log.info("Mock sendText on locator: {}, text: {}", locator, text);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDisplayed(String locator) {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public void waitObject(String locator) {
        log.info("Mock waitObject on locator: {}", locator);
    }
}
