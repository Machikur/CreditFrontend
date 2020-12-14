package com.bankfrontend.view.payment;

import com.bankfrontend.AppStatics;
import com.bankfrontend.domain.Account;
import com.bankfrontend.domain.PaymentType;
import com.bankfrontend.service.AccountService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Buttons extends HorizontalLayout implements PaymentObservable {

    private final List<PaymentObserver> paymentObservers = new ArrayList<>();
    private final ComboBox<String> accountBox = new ComboBox<>("Wybrane konto");

    public Buttons(PaymentObserver observer, AccountService accountService) {
        registerObserver(observer);
        ComboBox<PaymentType> paymentOptionComboBox = new ComboBox<>("Wybrane platności");
        List<String> accounts = accountService.getListOfAccounts().stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            accounts.add(0, AppStatics.ALL);
            accountBox.setItems(accounts);
            accountBox.setValue(accounts.get(0));
        }
        accountBox.addValueChangeListener(event -> notifyObservers(paymentOptionComboBox.getValue(), accountBox.getValue()));

        paymentOptionComboBox.setItems(PaymentType.values());
        paymentOptionComboBox.addValueChangeListener(event -> notifyObservers(paymentOptionComboBox.getValue(), accountBox.getValue()));
        paymentOptionComboBox.setValue(PaymentType.ALL);

        add(accountBox, paymentOptionComboBox);
    }

    @Override
    public List<PaymentObserver> getObservers() {
        return paymentObservers;
    }

}

