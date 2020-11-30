package com.bankfrontend.frontend.view.account;

import com.bankfrontend.frontend.domain.Observable;
import com.bankfrontend.frontend.domain.ObservableGrid;
import com.bankfrontend.frontend.domain.Observer;
import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.AccountService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.ArrayList;
import java.util.List;

public class Withdrawal extends VerticalLayout implements Observable {

    private final AccountService accountService = AccountService.getInstance();
    private final List<Observer> observers = new ArrayList<>();

    public Withdrawal(ObservableGrid observableGrid) {
        registerObserver(observableGrid);
        NumberField numberField = new NumberField("Kwota ", 0.0, null);
        Button withdrawalButton = new Button("wypłać", b -> {
            try {
                accountService.makeWithdrawal(observableGrid.getSelectedOptionId(), numberField.getValue());
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
