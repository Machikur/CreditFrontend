package com.bankfrontend.frontend.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreditOptions {
    private BigDecimal maxQuote;
    private List<CreditType> availableCreditTypes;
}
