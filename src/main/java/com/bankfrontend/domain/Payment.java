package com.bankfrontend.domain;

import com.bankfrontend.domain.currency.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {
    private Long paymentId;

    private Long accountFromId;

    private Long accountToId;

    private Long creditId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private BigDecimal quote;

    private Currency currency;

    public Payment(Long accountFromId, Long paymentDirectionId, BigDecimal quote, boolean creditPayment) {
        this.accountFromId = accountFromId;
        this.quote = quote;
        if (creditPayment) {
            this.creditId = paymentDirectionId;
        } else {
            this.accountToId = paymentDirectionId;
        }
    }


}
