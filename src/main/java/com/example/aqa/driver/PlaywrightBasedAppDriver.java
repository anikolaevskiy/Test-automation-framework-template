package com.example.aqa.driver;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link AppDriver} implementation backed by Playwright's {@link Page}.
 * <p>
 * The implementation mirrors the Selenium and Appium counterparts while
 * delegating operations to Playwright. Tests remain technology agnostic by
 * interacting only with the {@link AppDriver} interface.
 */
@Slf4j
@RequiredArgsConstructor
public class PlaywrightBasedAppDriver implements AppDriver {

    /** Underlying Playwright page executing commands in a browser. */
    private final Page page;

    /** {@inheritDoc} */
    @Override
    @Step("Click element located by {locator}")
    public void click(String locator) {
        log.info("Clicking on element with locator: {} by playwright driver", locator);
        page.locator(locator).click();
    }

    /** {@inheritDoc} */
    @Override
    @Step("Get text from element located by {locator}")
    public String getText(String locator) {
        log.info("Getting text from element with locator: {} by playwright driver", locator);
        return page.locator(locator).innerText();
    }

    /** {@inheritDoc} */
    @Override
    @Step("Send text {text} to element located by {locator}")
    public void sendText(String locator, String text) {
        log.info("Sending text '{}' to element with locator: {} by playwright driver", text, locator);
        page.locator(locator).fill(text);
    }

    /** {@inheritDoc} */
    @Override
    @Step("Check if element located by {locator} is displayed")
    public boolean isDisplayed(String locator) {
        return page.locator(locator).isVisible();
    }

    /** {@inheritDoc} */
    @Override
    @Step("Wait for element located by {locator}")
    public void waitObject(String locator) {
        log.info("Waiting for element with locator: {} by playwright driver", locator);
        page.locator(locator).waitFor();
    }
}

