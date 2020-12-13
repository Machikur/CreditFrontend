package com.bankfrontend.uri;

import com.bankfrontend.StaticsURLAndStrings;
import com.bankfrontend.domain.currency.Currency;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class CurrencyURL {

    public URI getUriForExchangeRates(Currency currency) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/exchangeRates")
                .queryParam("currency", currency)
                .build().encode().toUri();
    }

    public URI getUriForExchangeRate(Currency currencyFrom, Currency currencyTo, Double quote) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL + "/exchangeQuote")
                .queryParam("from", currencyFrom)
                .queryParam("to", currencyTo)
                .queryParam("quote", quote)
                .build().encode().toUri();
    }

}
