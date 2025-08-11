package com.example.aqa.app.client.object;

import com.example.aqa.driver.AppDriver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppObject {

    private final AppDriver appDriver;
    private final String locator;

    public void click() {
        appDriver.click(locator);
    }

    public String getText() {
        return appDriver.getText(locator);
    }

    public void sendText(String text) {
        appDriver.sendText(locator, text);
    }

    public void waitObject() {
        appDriver.waitObject(locator);
    }

}
