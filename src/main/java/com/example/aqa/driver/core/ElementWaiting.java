package com.example.aqa.driver.core;

/**
 * Interface for waiting for elements in an application driver.
 * <p>
 * This interface defines methods for waiting until elements are available
 * before performing actions on them. It is intended to be implemented by
 * specific application drivers like Selenium, Playwright, or Appium.
 */
public interface ElementWaiting {

    /**
     * Waits for an element to become available.
     *
     * @param locator element locator
     */
    void waitObject(String locator);
}
