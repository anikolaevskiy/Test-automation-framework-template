package com.example.aqa.app.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Something {

    @JsonProperty("string_value")
    private String stringValue;

    @JsonProperty("int_value")
    private int intValue;

}
