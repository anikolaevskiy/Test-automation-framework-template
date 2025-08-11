package com.example.aqa.app.client;

import com.example.aqa.app.client.object.AppObject;
import com.example.aqa.driver.AppDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageExample {

    private final AppDriver appDriver;

    public AppObject someButton() {
        return new AppObject(appDriver, "SomeButton");
    }

    public AppObject someLabel() {
        return new AppObject(appDriver, "SomeLabel");
    }
}
