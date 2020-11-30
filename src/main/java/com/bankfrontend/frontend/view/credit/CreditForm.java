package com.bankfrontend.frontend.view.credit;

import com.bankfrontend.frontend.domain.Observable;
import com.bankfrontend.frontend.domain.ObservableGrid;
import com.bankfrontend.frontend.domain.Observer;
import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.AccountService;
import com.bankfrontend.frontend.service.CreditService;
import com.bankfrontend.frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreditForm extends VerticalLayout implements Observable {
    private final AccountService accountService = AccountService.getInstance();
    private final CreditService creditService = CreditService.getInstance();
    private final List<Observer> observers = new ArrayList<>();
    private final ComboBox<String> accountNumbers = new ComboBox<>("Na wybrane konto");
    private final NumberField creditQuote = new NumberField("Kwota pożyczki", (double) 0, null);
    private final TextField interest = new TextField("Oprocentowanie");
    private final TextField quoteToPay = new TextField("Kwota do zapłaty");
    private final ComboBox<Integer> creditTypeComboBox = new ComboBox<>("Wybierz okres spłaty kredytu");

    public CreditForm(Observer... observers) {
        registerObserver(observers);

        quoteToPay.setReadOnly(true);
        interest.setReadOnly(true);

        List<String> accounts = accountService.getAccountNumbers();
        if (!accounts.isEmpty()) {
            accountNumbers.setItems(accounts);
            accountNumbers.setValue(accounts.get(0));
        }
        BigDecimal max = creditService.getUserCreditOptions().getMaxQuote();

        creditQuote.addValueChangeListener(event -> {
            if (creditQuote.getValue().compareTo(max.doubleValue()) > 0) {
                creditQuote.setValue(max.doubleValue());
            }
            updateInterestFields();
        });
        creditTypeComboBox.addValueChangeListener(event -> updateInterestFields());

        List<Integer> creditDays = creditService.getAvailableCreditDays();
        creditTypeComboBox.setItems(creditDays);
        creditTypeComboBox.setValue(creditDays.get(0));

        Button button = new Button("Weź kredyt", event -> {
            try {
                creditService.makeCredit(UserService.getUserId(),
                        accountService.getAccountIdByAccountNumber(accountNumbers.getValue()),
                        creditQuote.getValue(),
                        creditTypeComboBox.getValue());
                sleepAndUpdate();
            } catch (RestClientException s) {
                PopUp.throwErrorPopUp();
            }
        });
        VerticalLayout left = new VerticalLayout( accountNumbers, creditQuote,creditTypeComboBox);
        VerticalLayout right = new VerticalLayout( interest, quoteToPay,button);
        add(new Text("Weź nowy kredyt"),new HorizontalLayout(left,right));
        setAlignItems(Alignment.CENTER);
    }

    private void updateInterestFields() {
        double interestPercents = creditService.getInterestForUser(creditTypeComboBox.getValue());
        interest.setValue(interestPercents + "%");
        quoteToPay.setValue(creditQuote.getValue() + creditQuote.getValue() * interestPercents / 100 + "");
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }
}
