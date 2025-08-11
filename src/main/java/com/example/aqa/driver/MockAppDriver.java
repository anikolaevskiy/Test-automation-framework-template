package com.example.aqa.driver;

import lombok.extern.slf4j.Slf4j;

/***
 * Mock implementation of AppDriver for testing purposes.
 * This class simulates interactions with the application without performing real actions.
 * You can implement your own driver based on appium, selenium, playwright, etc.
 */
@Slf4j
public class MockAppDriver implements AppDriver{

    @Override
    public void click(String locator) {
        log.info("Mock click on locator: {}", locator);
    }

    @Override
    public String getText(String locator) {
        log.info("Mock getText on locator: {}", locator);
        return "Mock text value";
    }

    @Override
    public void sendText(String locator, String text) {
        log.info("Mock sendText on locator: {}, text: {}", locator, text);
    }

    @Override
    public void waitObject(String locator) {
        log.info("Mock waitObject on locator: {}", locator);
    }
}
