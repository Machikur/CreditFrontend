package com.bankfrontend.frontend.view.account;

import com.bankfrontend.frontend.domain.Payment;
import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.AccountService;
import com.bankfrontend.frontend.service.PaymentService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction extends VerticalLayout implements Observable {

    private final PaymentService paymentService = PaymentService.getInstance();
    private final AccountService accountService = AccountService.getInstance();
    private final List<Observer> observers = new ArrayList<>();

    public Transaction(ObservableGrid observableGrid) {
        registerObserver(observableGrid);

        Text title = new Text("Zrób przelew na konto ");
        TextField accountNumberField = new TextField("Na rachunek", "", "");
        NumberField quote = new NumberField("Wprowadź kwotę", (double) 0, null);
        NumberField pin = new NumberField("Wprowadź pin", (double) 0, null);

        Button accept = new Button("Wykonaj przelew", event -> {
            try {
                Payment payment = new Payment();
                payment.setAccountFromId(observableGrid.getSelectedOptionId());
                payment.setAccountToId(accountService.getAccountIdByAccountNumber(accountNumberField.getValue()));
                payment.setQuote(BigDecimal.valueOf(quote.getValue()));
                paymentService.makePayment(payment, pin.getValue().intValue());
                sleepAndUpdate();
            } catch (Exception s) {
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
