package com.example.aqa.driver;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link AppDriver} implementation backed by Playwright's {@link Page}.
 */
@Slf4j
public class PlaywrightBasedAppDriver extends AbstractAppDriver<Page> {

    public PlaywrightBasedAppDriver(Page page) {
        super(page);
    }

    @Override
    protected Element findElement(Page driver, Locator locator) {
        var element = driver.locator(locator.value());
        return new Element() {
            @Override
            public void click() { element.click(); }

            @Override
            public String getText() { return element.innerText(); }

            @Override
            public void sendText(String text) { element.fill(text); }

            @Override
            public boolean isDisplayed() { return element.isVisible(); }
        };
    }

    @Override
    protected void performWait(Page driver, Locator locator) {
        log.info("Waiting for element with locator: {} by playwright driver", locator.value());
        driver.locator(locator.value()).waitFor();
    }
}
