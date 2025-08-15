package com.example.aqa.driver.core;

/**
 * Base class for application drivers providing common functionality for
 * interacting with UI elements and waiting for conditions.
 * <p>
 * This class serves as a foundation for specific driver implementations like
 * Selenium, Playwright, and Appium, ensuring they all adhere to the same
 * Abstracted API for element interaction and waiting.
 */
public abstract class AppDriver implements ElementInteraction, ElementWaiting {
}
