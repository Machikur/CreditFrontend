package com.bankfrontend.uri;

import com.bankfrontend.StaticsURLAndStrings;
import com.bankfrontend.domain.currency.Currency;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class AccountURL {

    public URI makeDepositURI(Long accountId, double quote) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/account/deposit")
                .queryParam("accountId", accountId)
                .queryParam("quote", quote)
                .build().encode().toUri();
    }

    public URI withdrawalURI(Long accountId, double quote) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/account/withdrawal")
                .queryParam("accountId", accountId)
                .queryParam("quote", quote)
                .build().encode().toUri();
    }

    public URI saveAccountURI(Currency currency, Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/account")
                .queryParam("userId", userId)
                .queryParam("currency", currency)
                .build().encode().toUri();
    }

    public URI deleteAccountURI(Long accountId, int pinNumber) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/account")
                .queryParam("accountId", accountId)
                .queryParam("pinNumber", pinNumber)
                .build().encode().toUri();
    }

    public URI getAccountsForUserURI(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/accounts")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getCashBalanceForCurrencyURI(Currency currency, Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/account/currencyCash")
                .queryParam("userId", userId)
                .queryParam("currency", currency)
                .build().encode().toUri();
    }

}
