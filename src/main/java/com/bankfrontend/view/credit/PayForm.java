package com.bankfrontend.view.credit;

import com.bankfrontend.domain.Observer;
import com.bankfrontend.domain.Payment;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.AccountService;
import com.bankfrontend.service.CreditService;
import com.bankfrontend.service.PaymentService;
import com.bankfrontend.view.account.AccountObservable;
import com.bankfrontend.view.account.AccountObservableGrid;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PayForm extends VerticalLayout implements AccountObservable, Observer {

    private final AccountService accountService;
    private final PaymentService paymentService;
    private final CreditService creditService;
    private final List<Observer> observers = new ArrayList<>();
    private final ComboBox<Long> creditIdBox = new ComboBox<>("Id kredytu");

    public PayForm(AccountObservableGrid accountObservableGrid, AccountService accountService, PaymentService paymentService, CreditService creditService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
        this.creditService = creditService;
        registerObserver(accountObservableGrid);
        updateComponent();

        Text title = new Text("Zapłać za kredyt");

        List<String> accountNumbers = this.accountService.getAccountNumbers();
        ComboBox<String> accounts = new ComboBox<>("Wybierz konto do zapłaty", accountNumbers);
        if (!accountNumbers.isEmpty()) {
            accounts.setValue(accountNumbers.get(0));
        }

        NumberField quote = new NumberField("Kwota", (double) 0, null);

        NumberField pinNumber = new NumberField("Numer pin", (double) 0, null);

        Button submit = new Button("Zapłać", event -> {
            long id = this.accountService.getAccountIdByAccountNumber(accounts.getValue());
            Payment payment = new Payment(id,
                    (long) creditIdBox.getValue().intValue(),
                    BigDecimal.valueOf(quote.getValue()), true);
            try {
                this.paymentService.makePayment(payment, pinNumber.getValue().intValue());
                sleepAndUpdate();
            } catch (RestClientException s) {
                PopUp.throwErrorPopUp();
            }
        });
        VerticalLayout left = new VerticalLayout(accounts, creditIdBox);
        VerticalLayout right = new VerticalLayout(pinNumber, quote);
        HorizontalLayout middle = new HorizontalLayout(left, right);
        add(title, middle, submit);
        setAlignItems(Alignment.CENTER);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void updateComponent() {
        List<Long> list = creditService.getUserCreditsIds();
        creditIdBox.setItems(list);
        if (!list.isEmpty()) {
            creditIdBox.setValue(list.get(0));
        }
    }

}
