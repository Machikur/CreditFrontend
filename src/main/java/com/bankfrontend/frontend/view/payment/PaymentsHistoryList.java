package com.bankfrontend.frontend.view.payment;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import com.bankfrontend.frontend.domain.Payment;
import com.bankfrontend.frontend.domain.PaymentType;
import com.bankfrontend.frontend.service.PaymentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class PaymentsHistoryList extends VerticalLayout {

    private PaymentService paymentService = PaymentService.getInstance();
    private Grid<Payment> paymentGrid = setGrid();

    public PaymentsHistoryList(PaymentType paymentType, String accountNumber) {
        updatePayment(paymentType, accountNumber);
        add(paymentGrid);
    }

    public void updatePayment(PaymentType paymentType, String accountNumber) {
        List<Payment> payments;
        switch (accountNumber) {
            case StaticsURLAndStrings.ALL:
                payments = paymentService.getUserPayments(paymentType);
                break;
            default:
                payments = paymentService.getAccountPayments(accountNumber, paymentType);
                break;
        }
        switch (paymentType) {
            case CREDIT:
                paymentGrid.getColumnByKey("accountToId").setVisible(false);
                paymentGrid.getColumnByKey("creditId").setVisible(true);
                break;
            case ACCOUNT:
                paymentGrid.getColumnByKey("creditId").setVisible(false);
                paymentGrid.getColumnByKey("accountToId").setVisible(true);
                break;
            default:
                paymentGrid.getColumnByKey("accountToId").setVisible(true);
                paymentGrid.getColumnByKey("creditId").setVisible(true);
        }
        paymentGrid.setItems(payments);
        this.add(paymentGrid);
    }

    private Grid<Payment> setGrid() {
        Grid<Payment> paymentGrid = new Grid<>(Payment.class);
        paymentGrid.getColumnByKey("paymentId").setVisible(false);
        paymentGrid.getColumnByKey("accountFromId").setHeader("ID konta wyjściowego");
        paymentGrid.getColumnByKey("accountToId").setHeader("ID konta wejściowego");
        paymentGrid.getColumnByKey("creditId").setHeader("ID credytu");
        paymentGrid.getColumnByKey("currency").setHeader("Waluta");
        paymentGrid.getColumnByKey("quote").setHeader("Kwota");
        paymentGrid.getColumnByKey("createTime").setHeader("Data przelewu");
        return paymentGrid;
    }
}
