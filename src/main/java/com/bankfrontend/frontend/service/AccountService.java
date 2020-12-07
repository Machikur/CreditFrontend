package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.domain.Account;
import com.bankfrontend.frontend.domain.currency.Currency;
import com.bankfrontend.frontend.uri.AccountURL;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccountService {
    private static AccountService accountService;
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();
    private final AccountURL accountURL = new AccountURL();

    private AccountService() {
    }

    public static AccountService getInstance() {
        if (accountService == null) {
            synchronized (AccountService.class) {
                if (accountService == null) {
                    accountService = new AccountService();
                }
            }
        }
        return accountService;
    }

    public Long getAccountIdByAccountNumber(String accountNumber) {
        List<Account> accounts = getListOfAccounts();
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .map(Account::getId)
                .findAny()
                .orElse(-1L);
    }

    public Double getAllCashInCurrency(String currency) {
        return restTemplate.getForObject(accountURL.getCashBalanceForCurrencyURI(currency, UserService.getUserId()), Double.class);
    }

    public List<Account> getListOfAccounts() {
        Account[] accounts = restTemplate.getForObject(accountURL.getAccountsForUserURI(UserService.getUserId()), Account[].class);
        if (accounts != null) {
            return Arrays.asList(accounts);
        } else return new ArrayList<>();
    }

    public List<String> getAccountNumbers() {
        return accountService.getListOfAccounts().stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList());
    }

    public void createNewAccount(Currency currency) {
        restTemplate.postForObject(accountURL.saveAccountURI(currency, UserService.getUserId()), null, Account.class);
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
