package com.bankfrontend.uri;

import com.bankfrontend.AppStatics;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class PaymentURL {

    public URI getUserPaymentsURL(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/userPayments")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getAccountPaymentsURL(String accountNumber) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/accountPayments")
                .queryParam("accountNumber", accountNumber)
                .build().encode().toUri();
    }

    public URI makePaymentURL(long userId, int pinNumber) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/payment")
                .queryParam("userId", userId)
                .queryParam("pinNumber", pinNumber)
                .build().encode().toUri();
    }

}
