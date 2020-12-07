package com.bankfrontend.frontend.domain;

import com.bankfrontend.frontend.domain.currency.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    private Long paymentId;

    private Long accountFromId;

    private Long accountToId;

    private Long creditId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private BigDecimal quote;

    private Currency currency;

    public Payment(Long accountFromId, Long accountToId, BigDecimal quote) {
        this.accountFromId = accountFromId;
        this.accountToId = accountToId;
        this.quote = quote;
    }


}
