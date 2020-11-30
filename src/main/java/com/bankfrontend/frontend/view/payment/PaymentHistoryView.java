package com.bankfrontend.frontend.view.payment;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import com.bankfrontend.frontend.domain.Account;
import com.bankfrontend.frontend.domain.PaymentType;
import com.bankfrontend.frontend.service.AccountService;
import com.bankfrontend.frontend.service.PaymentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.stream.Collectors;

@Route("paymentHistory")
public class PaymentHistoryView extends VerticalLayout {

    private final PaymentService paymentService = PaymentService.getInstance();
    private AccountService accountService = AccountService.getInstance();
    private PaymentsHistoryList paymentsHistoryList;
    private ComboBox<String> accountBox = getAndUpdateAccounts();
    private ComboBox<PaymentType> paymentOptionComboBox = getPaymentOptionBox();
    private Button backButton = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));

    public PaymentHistoryView() {
        paymentsHistoryList = new PaymentsHistoryList(paymentOptionComboBox.getValue(), accountBox.getValue());
        HorizontalLayout horizontalLayout = new HorizontalLayout(accountBox, paymentOptionComboBox);
        add(horizontalLayout, paymentsHistoryList, backButton);
    }

    private ComboBox<String> getAndUpdateAccounts() {
        ComboBox<String> accountBox = new ComboBox<>();
        List<String> accounts = accountService.getListOfAccounts().stream()
                .map(Account::getAccountNumber)
                .collect(Collectors.toList());
        if (!accounts.isEmpty()) {
            accounts.add(0, StaticsURLAndStrings.ALL);
            accountBox.setItems(accounts);
            accountBox.setValue(accounts.get(0));
        }
        accountBox.setLabel("Wybrane konto");
        accountBox.addValueChangeListener(event -> paymentsHistoryList.updatePayment(paymentOptionComboBox.getValue(), accountBox.getValue()));
        return accountBox;
    }

    private ComboBox<PaymentType> getPaymentOptionBox() {
        ComboBox<PaymentType> comboBox = new ComboBox<>();
        comboBox.setItems(PaymentType.values());
        comboBox.setValue(PaymentType.ALL);
        comboBox.setLabel("Wybrane platności");
        comboBox.addValueChangeListener(event -> paymentsHistoryList.updatePayment(comboBox.getValue(), accountBox.getValue()));
        return comboBox;
    }


}
