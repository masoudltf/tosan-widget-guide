package com.tosan.modern.widget.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Data
public class WistoreLoginToken {

    @JsonProperty("token")
    private String token;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

}
