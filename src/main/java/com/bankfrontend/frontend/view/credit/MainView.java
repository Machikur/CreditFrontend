package com.bankfrontend.frontend.view.credit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Kredyty")
@Route("credit")
public class MainView extends VerticalLayout {
    private final CreditList creditList = new CreditList();

    public MainView() {
        PayForm payForm = new PayForm(creditList);
        CreditForm creditForm = new CreditForm(creditList, payForm);
        HorizontalLayout top = new HorizontalLayout(creditForm, payForm);
        BottomButtons bottomButtons = new BottomButtons(creditList);
        add(top, creditList, bottomButtons);
    }


}
