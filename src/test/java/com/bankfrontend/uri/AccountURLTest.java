package com.bankfrontend.uri;

import com.bankfrontend.domain.currency.Currency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountURLTest {

    @Autowired
    private AccountURL accountURL;

    @Test
    public void makeDepositURITest() {
        //given
        double quote = 12.0;
        Long id = 1L;

        // when
        URI uri = accountURL.makeDepositURI(id, quote);
        String link = uri.toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(link));
        Assert.assertTrue(link.contains(id.toString()));
        Assert.assertTrue(link.contains(Double.toString(quote)));
    }

    @Test
    public void withdrawalURITest() {
        //given
        double quote = 12.0;
        Long id = 1L;

        // when
        URI uri = accountURL.withdrawalURI(id, quote);
        String link = uri.toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(link));
        Assert.assertTrue(link.contains(id.toString()));
        Assert.assertTrue(link.contains(Double.toString(quote)));
    }

    @Test
    public void saveAccountURITest() {
        //given
        Currency currency = Currency.PLN;
        Long id = 1L;

        // when
        URI uri = accountURL.saveAccountURI(currency, id);
        String link = uri.toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(link));
        Assert.assertTrue(link.contains(id.toString()));
        Assert.assertTrue(link.contains(currency.toString()));
    }

    @Test
    public void getCashBalanceForCurrencyURITest() {
        //given
        Currency currency = Currency.PLN;
        Long id = 1L;

        // when
        URI uri = accountURL.getCashBalanceForCurrencyURI(currency, id);
        String link = uri.toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(link));
        Assert.assertTrue(link.contains(id.toString()));
        Assert.assertTrue(link.contains(currency.getDesc()));
    }

    @Test
    public void deleteAccountURITest() {
        //given
        int pin = 1233;
        Long id = 1L;

        // when
        URI uri = accountURL.deleteAccountURI(id, pin);
        String link = uri.toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(link));
        Assert.assertTrue(link.contains(id.toString()));
        Assert.assertTrue(link.contains(Integer.valueOf(pin).toString()));
    }

    @Test
    public void getAccountsForUserURITest() {
        //given

        Long id = 1L;

        // when
        URI uri = accountURL.getAccountsForUserURI(id);
        String link = uri.toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(link));
        Assert.assertTrue(link.contains(id.toString()));
    }

}