package com.tosan.modern.widget.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Data
public class Deposit {

    @JsonProperty("deposit_number")
    private String depositNumber;
    private BigDecimal balance;

}
