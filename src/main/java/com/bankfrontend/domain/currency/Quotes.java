package com.bankfrontend.domain.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {

    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private Rates rates;

}
