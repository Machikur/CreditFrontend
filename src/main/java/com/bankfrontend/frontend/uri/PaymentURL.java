package com.bankfrontend.frontend.uri;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class PaymentURL {

    public URI getUserPaymentsUri(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/userPayments")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getAccountPaymentsUri(String accountNumber) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/accountPayments")
                .queryParam("accountNumber", accountNumber)
                .build().encode().toUri();
    }

    public URI makePaymentUri(int pinNumber) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/payment")
                .queryParam("pinNumber", pinNumber)
                .build().encode().toUri();
    }

}
