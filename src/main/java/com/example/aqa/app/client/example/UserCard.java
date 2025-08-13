package com.example.aqa.app.client.example;

import com.example.aqa.app.client.object.AppObject;
import com.example.aqa.driver.AppDriver;

public class UserCard extends AppObject {

    public UserCard(AppDriver appDriver, String locator) {
        super(appDriver, locator);
    }

    public AppObject avatar() { return new AppObject(appDriver, "//figure[@class='image is-128x128']"); }

    public AppObject publicReposNumber() { return new AppObject(appDriver, "(//p[@class='title is-5'])[1]"); }

}
