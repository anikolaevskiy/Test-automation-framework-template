package com.example.aqa.driver;

/***
 * App driver abstraction for application testing.
 */
public interface AppDriver {

    void click(String locator);

    String getText(String locator);

    void sendText(String locator, String text);

    void waitObject(String locator);

}
