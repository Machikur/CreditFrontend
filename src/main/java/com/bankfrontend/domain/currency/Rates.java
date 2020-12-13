package com.bankfrontend.domain.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Rates {

    @JsonProperty("GBP")
    private double GBP;

    @JsonProperty("PLN")
    private double PLN;

    @JsonProperty("EUR")
    private double EUR;

    @JsonProperty("USD")
    private double USD;

}