package com.example.aqa.driver.core;

public interface ScreenshotCapture {

    /**
     * Takes a screenshot of the current application state.
     *
     * @return byte array containing the screenshot data
     */
    byte[] takeScreenshot();
}
