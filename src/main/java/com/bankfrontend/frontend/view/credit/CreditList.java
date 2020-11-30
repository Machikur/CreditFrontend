package com.bankfrontend.frontend.view.credit;

import com.bankfrontend.frontend.domain.Credit;
import com.bankfrontend.frontend.service.CreditService;
import com.bankfrontend.frontend.view.account.ObservableGrid;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CreditList extends VerticalLayout implements ObservableGrid {
    private final Grid<Credit> creditGrid = setGrid();
    private final CreditService creditService = CreditService.getInstance();

    public CreditList() {
        updateComponent();
        add(creditGrid);
    }

    private Grid<Credit> setGrid() {
        Grid<Credit> creditGrid = new Grid<>(Credit.class);
        creditGrid.getColumnByKey("userId").setVisible(false);
        creditGrid.getColumnByKey("payments").setVisible(false);
        creditGrid.getColumnByKey("amountToPay").setHeader("Kwota do zapłaty");
        creditGrid.getColumnByKey("amountPaid").setHeader("Zapłacono");
        creditGrid.getColumnByKey("currency").setHeader("Waluta kredytu");
        creditGrid.getColumnByKey("finishTime").setHeader("Czas do spłaty");
        creditGrid.getColumnByKey("startTime").setHeader("Czas rozpoczęcia");
        creditGrid.getColumnByKey("finished").setHeader("Zakończono");
        return creditGrid;
    }


    @Override
    public Long getSelectedOptionId() {
        return creditGrid.getSelectedItems().stream()
                .mapToLong(Credit::getCreditId)
                .findAny()
                .orElse(-1L);
    }

    @Override
    public void updateComponent() {
        creditGrid.setItems(creditService.getUserCredits());
    }

}
