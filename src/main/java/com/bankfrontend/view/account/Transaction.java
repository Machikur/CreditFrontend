package com.bankfrontend.view.account;

import com.bankfrontend.domain.Observer;
import com.bankfrontend.domain.Payment;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.AccountService;
import com.bankfrontend.service.PaymentService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction extends VerticalLayout implements AccountObservable {

    private final PaymentService paymentService;
    private final AccountService accountService;
    private final List<Observer> observers = new ArrayList<>();

    public Transaction(AccountObservableGrid accountObservableGrid, PaymentService paymentService, AccountService accountService) {
        this.paymentService = paymentService;
        this.accountService = accountService;
        registerObserver(accountObservableGrid);

        Text title = new Text("Zrób przelew na konto ");
        TextField accountNumberField = new TextField("Na rachunek", "", "");
        NumberField quote = new NumberField("Wprowadź kwotę", (double) 0, event->{});
        NumberField pin = new NumberField("Wprowadź pin", (double) 0, event->{});

        Button accept = new Button("Wykonaj przelew", event -> {
            try {
                long id = accountObservableGrid.getSelectedOptionId();
                Payment payment = new Payment(id,
                        accountService.getAccountIdByAccountNumber(accountNumberField.getValue()),
                        BigDecimal.valueOf(quote.getValue()),
                        false);
                this.paymentService.makePayment(payment, pin.getValue().intValue());
                sleepAndUpdate();
            } catch (Exception s) {
                System.out.println(s.getMessage());
                PopUp.throwErrorPopUp();
            }
        });
        HorizontalLayout middle = new HorizontalLayout(quote, pin);
        add(title, middle, accountNumberField, accept);
        setAlignItems(Alignment.CENTER);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

}
