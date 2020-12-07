package com.bankfrontend.frontend.domain;

import com.bankfrontend.frontend.domain.currency.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter

@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    private Long creditId;

    private Long userId;

    private BigDecimal amountToPay;

    private Currency currency;

    private BigDecimal amountPaid;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishTime;

    private boolean isFinished;

    private List<Payment> payments;
}
