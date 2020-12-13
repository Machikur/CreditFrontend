package com.bankfrontend.view.credit;

import com.bankfrontend.domain.Observer;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.CreditService;
import com.bankfrontend.view.account.AccountObservable;
import com.bankfrontend.view.account.AccountObservableGrid;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class BottomButtons extends HorizontalLayout implements AccountObservable {

    private final CreditService creditService;
    private final List<Observer> observers = new ArrayList<>();

    public BottomButtons(AccountObservableGrid accountObservableGrid, CreditService creditService) {
        this.creditService = creditService;
        registerObserver(accountObservableGrid);
        Button back = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        Button delete = new Button("usuń zaznaczony kredyt", b -> {
            try {
                this.creditService.deleteCredit(accountObservableGrid.getSelectedOptionId());
                notifyObservers();
            } catch (RestClientException e) {
                PopUp.throwPopUp("Kredyt musi być spłacony aby go usunąć");
            }
        });
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(new HorizontalLayout(back, delete));
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

}
