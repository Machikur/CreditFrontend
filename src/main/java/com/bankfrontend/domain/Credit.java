package com.bankfrontend.domain;

import com.bankfrontend.domain.currency.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
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
