package com.example.aqa.app.client.object;

import com.example.aqa.driver.AppDriver;
import lombok.RequiredArgsConstructor;

/**
 * Represents a page element in the application under test.
 * The element is identified by a locator and interacted with through
 * an {@link AppDriver} implementation.
 */
@RequiredArgsConstructor
public class AppObject {

    /** Driver used to perform actions on the element. */
    private final AppDriver appDriver;

    /** Locator used to identify the element in the application. */
    private final String locator;

    /**
     * Performs a click action on the element represented by this object.
     */
    public void click() {
        appDriver.click(locator);
    }

    /**
     * Retrieves text content from the element.
     *
     * @return text value of the element
     */
    public String getText() {
        return appDriver.getText(locator);
    }

    /**
     * Sends the provided text to the element.
     *
     * @param text text to send to the element
     */
    public void sendText(String text) {
        appDriver.sendText(locator, text);
    }

    /**
     * Waits until the element becomes available in the application.
     */
    public void waitObject() {
        appDriver.waitObject(locator);
    }

}
