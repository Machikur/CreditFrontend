package com.bankfrontend.view.payment;

import com.bankfrontend.StaticsURLAndStrings;
import com.bankfrontend.domain.Payment;
import com.bankfrontend.domain.PaymentType;
import com.bankfrontend.service.PaymentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class PaymentsHistoryList extends VerticalLayout implements PaymentObserver {

    private final PaymentService paymentService;
    private final Grid<Payment> paymentGrid = setGrid();

    public PaymentsHistoryList(PaymentService paymentService) {
        this.paymentService = paymentService;
        add(paymentGrid);
    }

    @Override
    public void updateComponent(PaymentType paymentType, String accountNumber) {
        List<Payment> payments;
        if (StaticsURLAndStrings.ALL.equals(accountNumber)) {
            payments = paymentService.getUserPayments(paymentType);
        } else {
            payments = paymentService.getAccountPayments(accountNumber, paymentType);
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
