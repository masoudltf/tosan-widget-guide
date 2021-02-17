package com.tosan.modern.widget.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Data
public class Customer {

    private String name;

    @JsonProperty("code")
    private String nationalCode;

    private String gender;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("customer_number")
    private String customerNumber;

    private String bankCode;

    private String loginToken;

}
