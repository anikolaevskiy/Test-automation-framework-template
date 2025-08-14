package com.example.aqa.app.server.client.feign;

import com.example.aqa.app.server.model.GitHubUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gitHubClient", url = "${server.host}")
public interface GitHubFeignClient {

    /**
     * Retrieves information about a GitHub user.
     *
     * @param username GitHub username to get information about
     * @return GitHub user information
     */
    @GetMapping(path = "/users/{username}")
    GitHubUser getUser(@PathVariable String username);
}
