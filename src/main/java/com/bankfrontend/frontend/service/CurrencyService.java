package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.domain.currency.Quotes;
import com.bankfrontend.frontend.uri.CurrencyURL;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

@Getter
public class CurrencyService {

    private static CurrencyService currencyService;
    private final CurrencyURL currencyURL = new CurrencyURL();
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();

    private CurrencyService() {
    }

    public static CurrencyService getInstance() {
        if (currencyService == null) {
            synchronized (CurrencyService.class) {
                if (currencyService == null) {
                    currencyService = new CurrencyService();
                }
            }
        }
        return currencyService;
    }

    public Quotes getCurrencies(String currency) {
        return restTemplate.getForObject(currencyURL.getUriForExchangeRates(currency), Quotes.class);
    }

    public Double countQuote(String currencyFrom, String currencyTo, Double quote) {
        return restTemplate.getForObject(currencyURL.getUriForExchangeRate(currencyFrom, currencyTo, quote), Double.class);
    }

}
