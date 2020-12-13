package com.bankfrontend.service;

import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.domain.currency.Quotes;
import com.bankfrontend.domain.currency.Rates;
import com.bankfrontend.uri.CurrencyURL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CurrencyURL currencyURL;

    @Test
    public void getCurrenciesTest() {
        //given
        Quotes quotes = new Quotes("pln", new Rates(1.0, 2.0, 3.0, 4.0));
        when(restTemplate.getForObject(any(), any())).thenReturn(quotes);

        //when
        Quotes result = currencyService.getCurrencies(Currency.PLN);

        //then
        Assert.assertNotNull(result);
        Assert.assertEquals("pln", result.getBase());
        Assert.assertEquals(3.0, result.getRates().getEUR(), 0.1);
        Assert.assertEquals(1.0, result.getRates().getGBP(), 0.1);
        Assert.assertEquals(2.0, result.getRates().getPLN(), 0.1);
        Assert.assertEquals(4.0, result.getRates().getUSD(), 0.1);
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

    @Test
    public void countQuoteTest() {
        //given
        when(restTemplate.getForObject(any(), any())).thenReturn(20.0);

        //when
        Double result = currencyService.countQuote(Currency.PLN,Currency.EUR, 2d);

        //then
        Assert.assertNotNull(result);
        Assert.assertEquals(20.0, result, 0.1);
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

}