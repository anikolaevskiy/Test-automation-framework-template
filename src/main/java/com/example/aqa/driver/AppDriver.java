package com.example.aqa.driver;

/**
 * App driver abstraction for application testing.
 * <p>
 * Tests interact with the application exclusively through this interface
 * allowing different driver technologies to be swapped in and out. Keeping the
 * contract minimal encourages small, focused page objects while still enabling
 * complex implementations underneath (Appium, Selenium, mock drivers, etc.).
 */
public interface AppDriver {

    /**
     * Clicks an element identified by the provided locator.
     *
     * @param locator element locator
     */
    void click(String locator);

    /**
     * Retrieves text from an element.
     *
     * @param locator element locator
     * @return text from the element
     */
    String getText(String locator);

    /**
     * Sends text to an element identified by the locator.
     *
     * @param locator element locator
     * @param text text to send
     */
    void sendText(String locator, String text);

    /**
     * Waits for an element to become available.
     *
     * @param locator element locator
     */
    void waitObject(String locator);

}
