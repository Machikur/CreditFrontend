package com.bankfrontend.service;

import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.domain.currency.Quotes;
import com.bankfrontend.uri.CurrencyURL;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@UIScope
@SpringComponent
public class CurrencyService {

    private final CurrencyURL currencyURL;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(CurrencyURL currencyURL, RestTemplate restTemplate) {
        this.currencyURL = currencyURL;
        this.restTemplate = restTemplate;
    }

    public Quotes getCurrencies(Currency currency) {
        return restTemplate.getForObject(currencyURL.getUriForExchangeRates(currency), Quotes.class);
    }

    public Double countQuote(Currency currencyFrom, Currency currencyTo, Double quote) {
        return restTemplate.getForObject(currencyURL.getUriForExchangeRate(currencyFrom, currencyTo, quote), Double.class);
    }

}
