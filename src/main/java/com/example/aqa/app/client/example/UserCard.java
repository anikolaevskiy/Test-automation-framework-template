package com.example.aqa.app.client.example;

import com.example.aqa.app.client.object.AppObject;
import com.example.aqa.driver.core.AppDriver;

/**
 * Page object representing a user's summary card displayed after performing a search.
 */
public class UserCard extends AppObject {

    /**
     * Creates a new user card page object.
     *
     * @param appDriver driver used for element interactions
     * @param locator locator identifying the user card root element
     */
    public UserCard(AppDriver appDriver, String locator) {
        super(appDriver, locator);
    }

    /**
     * Returns the avatar image element within the user card.
     *
     * @return {@link AppObject} for the user avatar
     */
    public AppObject avatar() { return new AppObject(appDriver, "//figure[@class='image is-128x128']"); }

    /**
     * Returns the element displaying the number of public repositories.
     *
     * @return {@link AppObject} for the public repositories number
     */
    public AppObject publicReposNumber() { return new AppObject(appDriver, "(//p[@class='title is-5'])[1]"); }

}
