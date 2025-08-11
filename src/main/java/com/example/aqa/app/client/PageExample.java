package com.example.aqa.app.client;

import com.example.aqa.app.client.object.AppObject;
import com.example.aqa.driver.AppDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Example page object that exposes application elements as {@link AppObject}s.
 */
@Component
@RequiredArgsConstructor
public class PageExample {

    /** Driver used to create page elements. */
    private final AppDriver appDriver;

    /**
     * Provides an {@link AppObject} representing a button.
     *
     * @return page element for the example button
     */
    public AppObject someButton() {
        return new AppObject(appDriver, "SomeButton");
    }

    /**
     * Provides an {@link AppObject} representing a label.
     *
     * @return page element for the example label
     */
    public AppObject someLabel() {
        return new AppObject(appDriver, "SomeLabel");
    }
}
