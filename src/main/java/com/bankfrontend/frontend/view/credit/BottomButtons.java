package com.bankfrontend.frontend.view.credit;

import com.bankfrontend.frontend.domain.Observable;
import com.bankfrontend.frontend.domain.ObservableGrid;
import com.bankfrontend.frontend.domain.Observer;
import com.bankfrontend.frontend.popup.PopUp;
import com.bankfrontend.frontend.service.CreditService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class BottomButtons extends HorizontalLayout implements Observable {

    private final CreditService creditService = CreditService.getInstance();
    private final List<Observer> observers = new ArrayList<>();

    public BottomButtons(ObservableGrid observableGrid) {
        registerObserver(observableGrid);
        Button back = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        Button delete = new Button("usuń zaznaczony kredyt", b -> {
            try {
                creditService.deleteCredit(observableGrid.getSelectedOptionId());
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
