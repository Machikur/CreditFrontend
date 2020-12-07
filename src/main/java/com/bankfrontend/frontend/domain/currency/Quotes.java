package com.bankfrontend.frontend.domain.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {

    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private Rates rates;


}
