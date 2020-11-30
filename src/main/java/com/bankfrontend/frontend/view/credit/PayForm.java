package com.bankfrontend.frontend.view.credit;

import com.bankfrontend.frontend.domain.Payment;
import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.AccountService;
import com.bankfrontend.frontend.service.CreditService;
import com.bankfrontend.frontend.service.PaymentService;
import com.bankfrontend.frontend.view.account.Observable;
import com.bankfrontend.frontend.view.account.ObservableGrid;
import com.bankfrontend.frontend.view.account.Observer;
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

public class PayForm extends VerticalLayout implements Observable, Observer {

    private final AccountService accountService = AccountService.getInstance();
    private final PaymentService paymentService = PaymentService.getInstance();
    private final CreditService creditService = CreditService.getInstance();
    private final List<Observer> observers = new ArrayList<>();
    private final ComboBox<Long> creditIdBox = new ComboBox<>("Id kredytu");

    public PayForm(ObservableGrid observableGrid) {
        registerObserver(observableGrid);
        updateComponent();

        Text title = new Text("Zapłać za kredyt");

        List<String> accountNumbers = accountService.getAccountNumbers();
        ComboBox<String> accounts = new ComboBox<>("Wybierz konto do zapłaty", accountNumbers);
        if (!accountNumbers.isEmpty()) {
            accounts.setValue(accountNumbers.get(0));
        }

        NumberField quote = new NumberField("Kwota", (double) 0, null);

        NumberField pinNumber = new NumberField("Numer pin", (double) 0, null);

        Button submit = new Button("Zapłać", event -> {
            Payment payment = new Payment(accountService.getAccountIdByAccountNumber(accounts.getValue()),
                    (long) creditIdBox.getValue().intValue(),
                    BigDecimal.valueOf(quote.getValue()));
            try {
                paymentService.makePayment(payment, pinNumber.getValue().intValue());
                sleepAndUpdate();
            } catch (RestClientException s) {
                PopUp.throwErrorPopUp();
            }
        });
        VerticalLayout left = new VerticalLayout(accounts, creditIdBox);
        VerticalLayout right = new VerticalLayout(quote, pinNumber);
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
