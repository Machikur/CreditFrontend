package com.bankfrontend.frontend.view.login;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Logowanie")
@Route("login")
public class MainView extends VerticalLayout {

    private UserForm userForm = new UserForm();
    private LoginView loginView = new LoginView();

    public MainView() {
        HorizontalLayout mainContent = new HorizontalLayout(loginView, userForm);
        add(mainContent);
        setSizeFull();
    }

}
