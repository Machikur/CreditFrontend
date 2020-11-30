package com.bankfrontend.frontend.uri;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import com.bankfrontend.frontend.domain.Currency;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class AccountURL {

    public URI makeDepositURI(Long accountId, double quote) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL + "/deposit")
                .queryParam("accountId", accountId)
                .queryParam("quote", quote)
                .build().encode().toUri();
    }

    public URI withdrawalURI(Long accountId, double quote) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL + "/withdrawal")
                .queryParam("accountId", accountId)
                .queryParam("quote", quote)
                .build().encode().toUri();
    }

    public URI saveAccountURI(Currency currency, Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL)
                .queryParam("userId", userId)
                .queryParam("currency", currency)
                .build().encode().toUri();
    }

    public URI deleteAccountURI(Long accountId, int pinNumber) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL)
                .queryParam("accountId", accountId)
                .queryParam("pinNumber", pinNumber)
                .build().encode().toUri();
    }

    public URI getAccountIdURI(Long accountId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL + "Id")
                .queryParam("accountId", accountId)
                .build().encode().toUri();
    }

    public URI getAccountsForUserURI(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL + "s")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getCashBalanceForCurrencyURI(String currency, Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.ACCOUNT_URL + "/currencyCash")
                .queryParam("userId", userId)
                .queryParam("currency", currency)
                .build().encode().toUri();
    }
}
