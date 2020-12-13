package com.bankfrontend.view.account;

import com.bankfrontend.service.AccountService;
import com.bankfrontend.service.PaymentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@PageTitle("Konta")
@Route("account")
public class MainView extends VerticalLayout {

    public MainView(AccountService accountService, PaymentService paymentService) {
        AccountsList accountsList = new AccountsList(accountService);
        NewAccountForm newAccountForm = new NewAccountForm(accountsList, accountService);
        Payment payment = new Payment(accountsList, accountService);
        Withdrawal withdrawal = new Withdrawal(accountsList, accountService);
        Transaction transaction = new Transaction(accountsList, paymentService, accountService);
        HorizontalLayout top = new HorizontalLayout(newAccountForm, payment, withdrawal, transaction);
        top.setSizeFull();
        Button back = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        DeleteAccountForm deleteAccountForm = new DeleteAccountForm(accountsList, accountService);
        add(top, accountsList, deleteAccountForm, back);
    }

}

