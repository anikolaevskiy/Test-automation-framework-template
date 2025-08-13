package com.example.aqa.app.client.example;

import com.example.aqa.app.client.object.AppObject;
import com.example.aqa.driver.AppDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElementsPage {

    private final AppDriver appDriver;

    public AppObject searchButton() { return new AppObject(appDriver, "//button[@id='search']"); }

    public AppObject searchInput() { return new AppObject(appDriver, "//input[@name='username']"); }

    public UserCard userCard() { return new UserCard(appDriver, "(//div[@class='card-content'])[1]"); }

}
