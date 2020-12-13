package com.bankfrontend.uri;

import com.bankfrontend.domain.currency.Currency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyURLTest {

    @Autowired
    private CurrencyURL currencyURL;

    @Test
    public void getUriForExchangeRatesTest() {
        //given
        Currency currency = Currency.PLN;

        //when
        String result = currencyURL.getUriForExchangeRates(currency).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(currency.toString()));
    }

    @Test
    public void getUriForExchangeRateTest() {
        //given
        Currency currencyFrom = Currency.PLN;
        Currency currencyTo = Currency.EUR;
        Double quote = 20.0;

        //when
        String result = currencyURL.getUriForExchangeRate(currencyFrom, currencyTo, quote).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(currencyFrom.toString()));
        Assert.assertTrue(result.contains(currencyTo.toString()));
        Assert.assertTrue(result.contains(quote.toString()));
    }

}