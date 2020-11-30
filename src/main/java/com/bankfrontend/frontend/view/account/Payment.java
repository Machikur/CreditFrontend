package com.bankfrontend.frontend.view.account;

import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.AccountService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.ArrayList;
import java.util.List;

public class Payment extends VerticalLayout implements Observable {

    private final AccountService accountService = AccountService.getInstance();
    private final List<Observer> observers = new ArrayList<>();

    public Payment(ObservableGrid observableGrid) {
        registerObserver(observableGrid);
        NumberField numberField = new NumberField("Kwota wpłaty", 0.0, null);
        Button payButton = new Button("wpłać", b -> {
            try {
                accountService.makeDeposit(observableGrid.getSelectedOptionId(), numberField.getValue());
                sleepAndUpdate();
            } catch (Exception s) {
                PopUp.throwErrorPopUp();
            }
        });
        add(new Text("Wpłać gotówke na zaznaczone konto"), numberField, payButton);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

}
