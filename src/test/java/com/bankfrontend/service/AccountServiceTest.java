package com.bankfrontend.service;

import com.bankfrontend.domain.Account;
import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.uri.AccountURL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountURL accountURL;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserService userService;

    @Test
    public void getAccountIdByAccountNumberTest() {
        //given
        Account[] accounts = {getSimpleAccount()};
        String accountNumber = accounts[0].getAccountNumber();
        when(restTemplate.getForObject(any(), any())).thenReturn(accounts);

        //when
        long accountId = accountService.getAccountIdByAccountNumber(accountNumber);

        //then
        verify(restTemplate, times(1)).getForObject(any(), any());
        Assert.assertEquals(1L, accountId);

    }

    @Test
    public void getAllCashInCurrencyTest() {
        //given
        Currency currency = Currency.USD;
        double quote = 2000.0;
        when(restTemplate.getForObject(any(), any())).thenReturn(quote);

        //when
        double result = accountService.getAllCashInCurrency(currency);

        //then
        verify(restTemplate, times(1)).getForObject(any(), any());
        Assert.assertEquals(quote, result, 0.1);

    }

    @Test
    public void getListOfAccountsTest() {
        Account[] accounts = {getSimpleAccount(), getSimpleAccount()};
        when(restTemplate.getForObject(any(), any())).thenReturn(accounts);

        //when
        List<Account> accountList = accountService.getListOfAccounts();

        //then
        verify(restTemplate, times(1)).getForObject(any(), any());
        Assert.assertFalse(accountList.isEmpty());
        Assert.assertEquals(2, accountList.size());

    }

    @Test
    public void getAccountNumbersTest() {
        //given
        Account[] accounts = {getSimpleAccount(), getSimpleAccount()};
        when(restTemplate.getForObject(any(), any())).thenReturn(accounts);

        //when
        List<String> accountNumbers = accountService.getAccountNumbers();

        //then
        verify(restTemplate, times(1)).getForObject(any(), any());
        Assert.assertEquals(2, accountNumbers.size());
        Assert.assertTrue(accountNumbers.contains("2222"));

    }

    @Test
    public void createNewAccountTest() {
        //given
        Currency currency = Currency.USD;

        //when
        accountService.createNewAccount(currency);

        //then
        verify(restTemplate, times(1)).postForObject(any(), any(), any());
    }

    @Test
    public void deleteAccountTest() {
        //given
        long accountId = 1L;
        int pinCode = 1234;

        //when
        accountService.deleteAccount(accountId, pinCode);

        //then
        verify(restTemplate, times(1)).delete(any());
    }

    @Test
    public void makeDepositTest() {
        //given
        long accountId = 1L;
        double quote = 123;

        //when
        accountService.makeDeposit(accountId, quote);

        //then
        verify(restTemplate, times(1)).put(any(), any());
    }

    @Test
    public void makeWithdrawalTest() {
        //given
        long accountId = 1L;
        double quote = 123;

        //when
        accountService.makeWithdrawal(accountId, quote);

        //then
        verify(restTemplate, times(1)).put(any(), any());
    }

    private Account getSimpleAccount() {
        return new Account(1L, BigDecimal.TEN, 2L, Currency.PLN, "2222",
                1234, LocalDate.now(), new ArrayList<>(), new ArrayList<>());
    }

}