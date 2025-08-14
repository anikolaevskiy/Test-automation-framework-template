package com.example.aqa.app.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubUser(String login, int id, @JsonProperty("public_repos") int publicRepos) {
}
