package com.bankfrontend.frontend.view.login;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Logowanie")
@Route("login")
public class MainView extends VerticalLayout {

    public MainView() {
        UserForm userForm = new UserForm();
        LoginView loginView = new LoginView();
        HorizontalLayout mainContent = new HorizontalLayout(loginView, userForm);
        add(mainContent);
        setSizeFull();
    }

}
