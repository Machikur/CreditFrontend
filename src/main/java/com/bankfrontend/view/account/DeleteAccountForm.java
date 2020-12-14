package com.bankfrontend.view.account;

import com.bankfrontend.domain.Observer;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.AccountService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.ArrayList;
import java.util.List;

public class DeleteAccountForm extends VerticalLayout implements AccountObservable {

    private final AccountService accountService;
    private final List<Observer> observers = new ArrayList<>();

    public DeleteAccountForm(AccountObservableGrid accountObservableGrid, AccountService accountService) {
        this.accountService = accountService;
        registerObserver(accountObservableGrid);
        NumberField numberField = new NumberField("Kod pin", (double) 0, event -> {
        });
        Button deleteButton = new Button("Usuń konto", b -> {
            try {
                this.accountService.deleteAccount(accountObservableGrid.getSelectedOptionId(), numberField.getValue().intValue());
                sleepAndUpdate();
            } catch (Exception s) {
                PopUp.throwPopUp("Musisz wybrać wszystkie środki zanim zamkniesz konto a także wpisać poprawny pin");
            }
        });
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(new Text("Usuń zaznaczone konto jeśli jest puste"), numberField, deleteButton);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

}
