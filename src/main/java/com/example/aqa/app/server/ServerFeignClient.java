package com.example.aqa.app.server;

import com.example.aqa.app.server.model.Something;
import com.example.aqa.app.server.model.Token;
import com.example.aqa.app.server.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

/**
 * Feign declaration of the server API.
 */
@FeignClient(name = "serverClient", url = "${server.host}")
public interface ServerFeignClient {

    /**
     * Authenticates the given user.
     *
     * @param user credentials to authenticate
     * @return access token issued by the service
     */
    @PostMapping(path = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    Token auth(@RequestBody User user);

    /**
     * Retrieves an example entity from the server.
     *
     * @return {@link Something} returned by the API
     */
    @GetMapping("/something")
    Something getSomething();

    /**
     * Creates or replaces the example entity on the server.
     *
     * @param something entity to send to the API
     * @return stored entity representation
     */
    @PutMapping(path = "/something", consumes = MediaType.APPLICATION_JSON_VALUE)
    Something createSomething(@RequestBody Something something);
}
