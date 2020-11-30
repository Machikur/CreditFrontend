package com.bankfrontend.frontend.view.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("BankApp")
@Route("bank")
public class MainView extends HorizontalLayout {

    public MainView() {
        CurrenciesView currenciesView = new CurrenciesView();
        UserDetails userDetails = new UserDetails();
        add(userDetails, getOptionButtons(), currenciesView);
    }

    public VerticalLayout getOptionButtons() {
        Button accountButton = new Button("Przejdź do kont"
                , s -> UI.getCurrent().navigate("account"));

        Button paymentHistoryButton = new Button("Przejdź do histori płatności"
                , s -> UI.getCurrent().navigate("paymentHistory"));

        Button paymentButton = new Button("Przejdź do panelu przelewów"
                , s -> UI.getCurrent().navigate("payment"));

        Button creditButton = new Button("Przejdź do panelu kredytów"
                , s -> UI.getCurrent().navigate("credit"));

        Button userButton = new Button("Przejdź do panelu użytkownika"
                , s -> UI.getCurrent().navigate("user"));


        return new VerticalLayout(accountButton, paymentHistoryButton, paymentButton, creditButton, userButton);
    }
}
