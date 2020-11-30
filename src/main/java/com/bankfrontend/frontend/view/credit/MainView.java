package com.bankfrontend.frontend.view.credit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;

@PageTitle("Kredyty")
@Route("credit")
public class MainView extends VerticalLayout {
    private final CreditList creditList = new CreditList();
    private final PayForm payForm = new PayForm(creditList);
    private final CreditForm creditForm = new CreditForm(creditList,payForm);
    private final BottomButtons bottomButtons = new BottomButtons(creditList);

    public MainView() {
        HorizontalLayout top = new HorizontalLayout(creditForm, payForm);
        add(top, creditList, bottomButtons);
    }


}
