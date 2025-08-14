package com.example.aqa.app.server.client;

import com.example.aqa.app.server.model.Token;
import com.example.aqa.app.server.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
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

}
