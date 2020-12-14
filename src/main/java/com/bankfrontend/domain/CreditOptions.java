package com.bankfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditOptions {

    private BigDecimal maxQuote;

    private List<CreditType> availableCreditTypes;

}
