package com.bankfrontend.service;

import com.bankfrontend.domain.Account;
import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.uri.AccountURL;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class AccountService {

    private final RestTemplate restTemplate;
    private final AccountURL accountURL;
    private final UserService userService = UserService.getInstance();

    @Autowired
    public AccountService(RestTemplate restTemplate, AccountURL accountURL) {
        this.restTemplate = restTemplate;
        this.accountURL = accountURL;
    }

    public Long getAccountIdByAccountNumber(String accountNumber) {
        List<Account> accounts = getListOfAccounts();
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .map(Account::getId)
                .findAny()
                .orElse(-1L);
    }

    public Double getAllCashInCurrency(Currency currency) {
        return restTemplate.getForObject(accountURL.getCashBalanceForCurrencyURI(currency, userService.getUserId()), Double.class);
    }

    public List<Account> getListOfAccounts() {
        Account[] accounts = restTemplate.getForObject(accountURL.getAccountsForUserURI(userService.getUserId()), Account[].class);
        if (accounts != null) {
            return Arrays.asList(accounts);
        } else return new ArrayList<>();
    }

    public List<String> getAccountNumbers() {
        return getListOfAccounts().stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList());
    }

    public void createNewAccount(Currency currency) {
        restTemplate.postForObject(accountURL.saveAccountURI(currency, userService.getUserId()), null, Account.class);
    }

    public void deleteAccount(Long accountId, int pinNumber) {
        restTemplate.delete(accountURL.deleteAccountURI(accountId, pinNumber));
    }

    public void makeDeposit(Long accountId, double quote) {
        restTemplate.put(accountURL.makeDepositURI(accountId, quote), null);
    }

    public void makeWithdrawal(Long accountId, double quote) {
        restTemplate.put(accountURL.withdrawalURI(accountId, quote), null);
    }

}
