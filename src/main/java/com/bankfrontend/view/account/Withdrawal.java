package com.bankfrontend.view.account;

import com.bankfrontend.domain.Observer;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.AccountService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.ArrayList;
import java.util.List;

public class Withdrawal extends VerticalLayout implements AccountObservable {

    private final AccountService accountService;
    private final List<Observer> observers = new ArrayList<>();

    public Withdrawal(AccountObservableGrid accountObservableGrid, AccountService accountService) {
        this.accountService = accountService;
        registerObserver(accountObservableGrid);
        NumberField numberField = new NumberField("Kwota ", 0.0, event->{});
        Button withdrawalButton = new Button("wypłać", b -> {
            try {
                this.accountService.makeWithdrawal(accountObservableGrid.getSelectedOptionId(), numberField.getValue());
                sleepAndUpdate();
            } catch (Exception s) {
                PopUp.throwErrorPopUp();
            }
        });
        add(new Text("Wypłać pieniądze z zaznaczonego konta"), numberField, withdrawalButton);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

}
