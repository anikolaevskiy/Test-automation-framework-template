package com.example.aqa.app.server.client;

import com.example.aqa.app.server.client.feign.GitHubFeignClient;
import com.example.aqa.app.server.model.GitHubUser;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link RestApiClient} implementation backed by a Feign client.
 * <p>
 * keeping the rest of the framework technology-agnostic.
 */
@Slf4j
@RequiredArgsConstructor
public class FeignBasedRestApiClient implements RestApiClient {

    private final GitHubFeignClient gitHubFeignClient;

    @Override
    @Step("Get user information for username: {username}")
    public GitHubUser getUser(String username) {
        log.info("Fetching user information for username: {}", username);
        return gitHubFeignClient.getUser(username);
    }

}
