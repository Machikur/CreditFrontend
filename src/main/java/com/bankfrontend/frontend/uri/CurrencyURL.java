package com.bankfrontend.frontend.uri;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class CurrencyURL {

    public URI getUriForExchangeRates(String currency) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/exchangeRates")
                .queryParam("currency", currency)
                .build().encode().toUri();
    }

    public URI getUriForExchangeRate(String currencyFrom, String currencyTo, Double quote) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/exchangeQuote")
                .queryParam("from", currencyFrom)
                .queryParam("to", currencyTo)
                .queryParam("quote", quote)
                .build().encode().toUri();
    }
}
