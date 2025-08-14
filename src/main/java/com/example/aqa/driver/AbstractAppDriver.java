package com.example.aqa.driver;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

/**
 * Base implementation of {@link AppDriver} providing common actions.
 * <p>
 * Concrete drivers only need to implement element search and waiting
 * strategies while high level operations such as {@link #click(String)}
 * or {@link #getText(String)} are handled here.
 *
 * @param <T> underlying driver type (e.g. WebDriver, Page)
 */
@Slf4j
public abstract class AbstractAppDriver<T> implements AppDriver {

    /** Underlying technology specific driver. */
    protected final T driver;

    protected AbstractAppDriver(T driver) {
        this.driver = driver;
    }

    /**
     * Locates an element using the provided driver and locator.
     *
     * @param driver  the underlying driver instance
     * @param locator locator describing the element
     * @return wrapper providing unified element actions
     */
    protected abstract Element findElement(T driver, Locator locator);

    /**
     * Performs a wait strategy until the element is ready for interaction.
     *
     * @param driver  the underlying driver instance
     * @param locator locator describing the element
     */
    protected abstract void performWait(T driver, Locator locator);

    @Override
    @Step("Click element located by {locator}")
    public void click(String locator) {
        var loc = new Locator(locator);
        log.info("Clicking on element with locator: {}", locator);
        performWait(driver, loc);
        findElement(driver, loc).click();
    }

    @Override
    @Step("Get text from element located by {locator}")
    public String getText(String locator) {
        var loc = new Locator(locator);
        log.info("Getting text from element with locator: {}", locator);
        performWait(driver, loc);
        return findElement(driver, loc).getText();
    }

    @Override
    @Step("Send text {text} to element located by {locator}")
    public void sendText(String locator, String text) {
        var loc = new Locator(locator);
        log.info("Sending text '{}' to element with locator: {}", text, locator);
        performWait(driver, loc);
        findElement(driver, loc).sendText(text);
    }

    @Override
    @Step("Check if element located by {locator} is displayed")
    public boolean isDisplayed(String locator) {
        var loc = new Locator(locator);
        log.info("Check if element located by {} is displayed", locator);
        performWait(driver, loc);
        return findElement(driver, loc).isDisplayed();
    }

    @Override
    @Step("Wait for element located by {locator}")
    public void waitObject(String locator) {
        performWait(driver, new Locator(locator));
    }

    /**
     * Minimal abstraction over framework specific element implementations.
     * Each driver adapts its native element to this interface so the base
     * class can execute common actions.
     */
    protected interface Element {
        void click();
        String getText();
        void sendText(String text);
        boolean isDisplayed();
    }
}
