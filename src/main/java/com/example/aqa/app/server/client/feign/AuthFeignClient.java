package com.example.aqa.app.server.client.feign;

import com.example.aqa.app.server.client.RestApiClient;
import com.example.aqa.app.server.client.ServerFeignClient;
import com.example.aqa.app.server.model.Token;
import com.example.aqa.app.server.model.User;
import lombok.RequiredArgsConstructor;

/**
 * {@link RestApiClient} implementation backed by a Feign client.
 * <p>
 * Delegates all operations to the generated {@link ServerFeignClient} while
 * keeping the rest of the framework technology-agnostic.
 */
@RequiredArgsConstructor
public class AuthFeignClient implements RestApiClient {

    /** Feign client proxy for the server API. */
    private final ServerFeignClient serverFeignClient;

    /** {@inheritDoc} */
    @Override
    public Token auth(String username, String password) {
        return serverFeignClient.auth(new User(username, password));
    }

}
