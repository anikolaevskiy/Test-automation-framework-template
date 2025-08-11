package com.example.aqa.app.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple data transfer object used in REST API examples.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Something {

    /** Sample string value. */
    @JsonProperty("string_value")
    private String stringValue;

    /** Sample integer value. */
    @JsonProperty("int_value")
    private int intValue;

}
