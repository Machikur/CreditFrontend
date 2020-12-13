package com.bankfrontend.view.main;

import com.bankfrontend.service.AccountService;
import com.bankfrontend.service.CreditService;
import com.bankfrontend.service.CurrencyService;
import com.bankfrontend.service.FinanceService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("BankApp")
@Route("bank")
public class MainView extends HorizontalLayout {

    public MainView(AccountService accountService, CurrencyService currencyService, CreditService creditService, FinanceService financeService) {
        FinanceView financeView = new FinanceView(financeService);
        CurrenciesView currenciesView = new CurrenciesView(currencyService);
        UserDetails userDetails = new UserDetails(accountService, creditService);
        CurrenciesRate currenciesRate = new CurrenciesRate(currencyService);
        add(userDetails, getOptionButtons(), financeView, new VerticalLayout(currenciesRate, currenciesView));
    }

    public VerticalLayout getOptionButtons() {
        Button accountButton = new Button("Przejdź do kont"
                , s -> UI.getCurrent().navigate("account"));

        Button paymentHistoryButton = new Button("Przejdź do histori płatności"
                , s -> UI.getCurrent().navigate("paymentHistory"));

        Button creditButton = new Button("Przejdź do panelu kredytów"
                , s -> UI.getCurrent().navigate("credit"));

        Button userButton = new Button("Przejdź do panelu użytkownika"
                , s -> UI.getCurrent().navigate("user"));


        return new VerticalLayout(accountButton, paymentHistoryButton, creditButton, userButton);
    }

}
