package com.bankfrontend.frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceData {

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("price")
    private double price;

    @JsonProperty("changesPercentage")
    private String changesPercentage;

    @JsonProperty("companyName")
    private String companyName;
}