package com.bankfrontend.frontend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    private Long id;

    private BigDecimal cashBalance;

    private Long userId;

    private Currency currency;

    private String accountNumber;

    private int pinCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

    private List<Payment> paymentsFrom;

    private List<Payment> paymentsTo;
}
