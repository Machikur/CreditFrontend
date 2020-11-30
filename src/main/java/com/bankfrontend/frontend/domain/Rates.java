package com.bankfrontend.frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
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