package com.example.aqa.app.client.example;

import com.example.aqa.app.client.object.AppObject;
import com.example.aqa.driver.AppDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Page object representing the elements screen in the sample application.
 * <p>
 * Methods return lightweight {@link AppObject} instances for individual
 * controls, allowing tests to compose interactions fluently.
 */
@Component
@RequiredArgsConstructor
public class ElementsPage {

    /** Driver used to create page element objects. */
    private final AppDriver appDriver;

    /**
     * Returns the search button element.
     *
     * @return {@link AppObject} representing the search button
     */
    public AppObject searchButton() { return new AppObject(appDriver, "//button[@id='search']"); }

    /**
     * Returns the search input field element.
     *
     * @return {@link AppObject} representing the search input field
     */
    public AppObject searchInput() { return new AppObject(appDriver, "//input[@name='username']"); }

    /**
     * Returns the user card section containing user information.
     *
     * @return {@link UserCard} representing the user card
     */
    public UserCard userCard() { return new UserCard(appDriver, "(//div[@class='card-content'])[1]"); }

}
