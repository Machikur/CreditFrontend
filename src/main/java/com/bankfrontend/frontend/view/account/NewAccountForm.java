package com.bankfrontend.frontend.view.account;

import com.bankfrontend.frontend.domain.Currency;
import com.bankfrontend.frontend.domain.Observable;
import com.bankfrontend.frontend.domain.Observer;
import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.AccountService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class NewAccountForm extends VerticalLayout implements Observable {

    private final AccountService accountService = AccountService.getInstance();
    private final List<Observer> observers = new ArrayList<>();

    public NewAccountForm() {
        ComboBox<Currency> comboBox = new ComboBox<>("Waluta");
        comboBox.setItems(Currency.values());
        comboBox.setValue(Currency.PLN);
        Button button = new Button("Stworz nowe konto", b -> {
            try {
                accountService.createNewAccount(comboBox.getValue());
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
