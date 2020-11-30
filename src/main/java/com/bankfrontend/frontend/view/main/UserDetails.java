package com.bankfrontend.frontend.view.main;

import com.bankfrontend.frontend.domain.Currency;
import com.bankfrontend.frontend.service.AccountService;
import com.bankfrontend.frontend.service.CreditService;
import com.bankfrontend.frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.List;

public class UserDetails extends VerticalLayout {
    private final AccountService accountService = AccountService.getInstance();
    private final CreditService creditService = CreditService.getInstance();
    private final UserService userService = UserService.getInstance();

    public UserDetails() {
        Text name = new Text(" Zalogowano jako " + userService.getUserName() + " (Status użytkownika: " + userService.getUserStatus() + ")");
        VerticalLayout accountsSize = new VerticalLayout(new Text("Liczba aktywnych kont: " + accountService.getListOfAccounts().size()));
        VerticalLayout creditSize = new VerticalLayout(new Text("Liczba aktywnych kredytów: " + creditService.getUserCredits().size()));
        Button logout = new Button("Wyloguj"
                , s -> {
            userService.logout();
            UI.getCurrent().navigate("login");
        });
        add(name, getCashBalance(), accountsSize, creditSize, getUserIndebtedness(), logout);
    }


    private VerticalLayout getCashBalance() {
        VerticalLayout verticalLayout = new VerticalLayout();
        List<Currency> currencies = userService.getUserCurrencies();
        HorizontalLayout horizontalLayoutOne = new HorizontalLayout();
        HorizontalLayout horizontalLayoutTwo = new HorizontalLayout();
        if (!currencies.isEmpty()) {
            Text title = new Text("Suma środków na kontach");
            verticalLayout.add(title);
            int counter = 0;
            for (Currency c : currencies) {
                NumberField numberField = new NumberField(c.getDesc());
                numberField.setValue(accountService.getAllCashInCurrency(c.toString()));
                numberField.setReadOnly(true);
                if (counter < 2) {
                    horizontalLayoutOne.add(numberField);
                } else {
                    horizontalLayoutTwo.add(numberField);
                }
                counter++;
            }
            verticalLayout.add(horizontalLayoutOne, horizontalLayoutTwo);
        }
        return verticalLayout;
    }

    public VerticalLayout getUserIndebtedness() {
        VerticalLayout indebtedness = new VerticalLayout();
        List<Currency> currencies = userService.getUserCurrencies();
        if (!currencies.isEmpty()) {
            indebtedness.add("Bieżące zobowiązania");
            for (Currency c : currencies) {
                NumberField numberField = new NumberField(c.getDesc());
                numberField.setReadOnly(true);
                numberField.setValue(creditService.getUserIndebtedness(c));
                if (numberField.getValue() != 0) {
                    indebtedness.add(numberField);
                }
            }
        }
        return indebtedness;
    }


}
