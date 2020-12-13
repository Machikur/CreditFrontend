package com.bankfrontend.view.credit;

import com.bankfrontend.service.AccountService;
import com.bankfrontend.service.CreditService;
import com.bankfrontend.service.PaymentService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Kredyty")
@Route("credit")
public class MainView extends VerticalLayout {

    public MainView(CreditService creditService, PaymentService paymentService, AccountService accountService) {
        CreditList creditList = new CreditList(creditService);
        PayForm payForm = new PayForm(creditList, accountService, paymentService, creditService);
        CreditForm creditForm = new CreditForm(accountService, creditService, creditList, payForm);
        HorizontalLayout top = new HorizontalLayout(creditForm, payForm);
        BottomButtons bottomButtons = new BottomButtons(creditList, creditService);
        add(top, creditList, bottomButtons);
    }

}
