package com.example.aqa.driver;

/**
 * Simple value object representing a locator strategy.
 * <p>
 * For now it merely wraps the locator value but can be extended
 * in the future to support different lookup strategies (CSS, id, etc.).
 */
public record Locator(String value) {
}
