package com.bankfrontend.frontend.view.login;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Logowanie")
@Route("login")
public class MainView extends HorizontalLayout {

    public MainView() {
        ProjectInfo projectInfo = new ProjectInfo();
        UserForm userForm = new UserForm();
        LoginView loginView = new LoginView();
        VerticalLayout leftSide = new VerticalLayout(loginView, projectInfo);
        leftSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(userForm, loginView, projectInfo);
        setSizeFull();
    }

}
