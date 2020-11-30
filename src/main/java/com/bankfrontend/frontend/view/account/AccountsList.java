package com.bankfrontend.frontend.view.account;

import com.bankfrontend.frontend.domain.Account;
import com.bankfrontend.frontend.domain.ObservableGrid;
import com.bankfrontend.frontend.service.AccountService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AccountsList extends VerticalLayout implements ObservableGrid {

    private final Grid<Account> accountGrid = new Grid<>(Account.class);
    private final AccountService accountService = AccountService.getInstance();

    public AccountsList() {
        setAccountGrid();
        updateComponent();
        add(accountGrid);
    }

    private void setAccountGrid() {
        accountGrid.getColumnByKey("accountNumber").setHeader("Numer konta");
        accountGrid.getColumnByKey("currency").setHeader("Waluta");
        accountGrid.getColumnByKey("cashBalance").setHeader("Stan konta");
        accountGrid.getColumnByKey("createTime").setHeader("Utworzono");
        accountGrid.getColumnByKey("pinCode").setHeader("Kod pin");
        accountGrid.getColumnByKey("id").setVisible(false);
        accountGrid.getColumnByKey("userId").setVisible(false);
        accountGrid.getColumnByKey("paymentsFrom").setVisible(false);
        accountGrid.getColumnByKey("paymentsTo").setVisible(false);
    }

    @Override
    public Long getSelectedOptionId() {
        return accountGrid.getSelectedItems().stream()
                .map(Account::getId)
                .findAny()
                .orElse(-1L);
    }

    @Override
    public void updateComponent() {
        accountGrid.setItems(accountService.getListOfAccounts());
    }

}
