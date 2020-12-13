package com.bankfrontend.view.account;

import com.bankfrontend.domain.Observer;
import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.AccountService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class NewAccountForm extends VerticalLayout implements AccountObservable {

    private final AccountService accountService;
    private final List<Observer> observers = new ArrayList<>();

    public NewAccountForm(Observer observer, AccountService accountService) {
        this.accountService = accountService;
        registerObserver(observer);
        ComboBox<Currency> comboBox = new ComboBox<>("Waluta");
        comboBox.setItems(Currency.values());
        comboBox.setValue(Currency.PLN);
        Button button = new Button("Stworz nowe konto", b -> {
            try {
                this.accountService.createNewAccount(comboBox.getValue());
                sleepAndUpdate();
            } catch (Exception s) {
                PopUp.throwErrorPopUp();
            }
        });
        add(new Text("Stwórz nowe konto"), comboBox, button);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

}
