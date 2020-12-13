package com.bankfrontend.view.payment;

import com.bankfrontend.service.AccountService;
import com.bankfrontend.service.PaymentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Historia przelewów")
@Route("paymentHistory")
public class MainView extends VerticalLayout {

    public MainView(PaymentService paymentService, AccountService accountService) {
        Button backButton = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        PaymentsHistoryList paymentsHistoryList = new PaymentsHistoryList(paymentService);
        Buttons buttons = new Buttons(paymentsHistoryList, accountService);
        add(buttons, paymentsHistoryList, backButton);
    }

}
