package com.example.aqa.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * {@link AppDriver} implementation backed by an Appium {@link WebDriver}.
 */
@Slf4j
public class AppiumBasedAppDriver extends AbstractAppDriver<WebDriver> {

    private final WebDriverWait wait;

    public AppiumBasedAppDriver(WebDriver appiumDriver, WebDriverWait wait) {
        super(appiumDriver);
        this.wait = wait;
    }

    @Override
    protected Element findElement(WebDriver driver, Locator locator) {
        var element = driver.findElement(By.xpath(locator.value()));
        return new Element() {
            @Override
            public void click() { element.click(); }

            @Override
            public String getText() { return element.getText(); }

            @Override
            public void sendText(String text) { element.sendKeys(text); }

            @Override
            public boolean isDisplayed() { return element.isDisplayed(); }
        };
    }

    @Override
    protected void performWait(WebDriver driver, Locator locator) {
        log.info("Waiting for element with locator: {} by appium driver", locator.value());
        wait.until(d -> d.findElement(By.xpath(locator.value())).isDisplayed());
    }
}
