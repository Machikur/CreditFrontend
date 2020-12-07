package com.bankfrontend.frontend.view.payment;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Historia przelewów")
@Route("paymentHistory")
public class MainView extends VerticalLayout {

    public MainView() {
        Button backButton = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        PaymentsHistoryList paymentsHistoryList = new PaymentsHistoryList();
        Buttons buttons = new Buttons(paymentsHistoryList);
        add(buttons, paymentsHistoryList, backButton);
    }

}
