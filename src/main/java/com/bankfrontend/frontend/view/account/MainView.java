package com.bankfrontend.frontend.view.account;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Konta")
@Route("account")
public class MainView extends VerticalLayout {

    private final AccountsList accountsList = new AccountsList();

    public MainView() {
        NewAccountForm newAccountForm = new NewAccountForm();
        Payment payment = new Payment(accountsList);
        Withdrawal withdrawal = new Withdrawal(accountsList);
        Transaction transaction = new Transaction(accountsList);
        HorizontalLayout top = new HorizontalLayout(newAccountForm, payment, withdrawal, transaction);
        top.setSizeFull();
        Button back = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        DeleteAccountForm deleteAccountForm = new DeleteAccountForm(accountsList);
        add(top, accountsList, deleteAccountForm, back);
    }

}

