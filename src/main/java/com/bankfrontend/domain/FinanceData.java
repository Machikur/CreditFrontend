package com.bankfrontend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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