package com.bankfrontend.view.login;

import com.bankfrontend.service.InfoService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Logowanie")
@Route("login")
public class MainView extends HorizontalLayout {

    public MainView(InfoService infoService) {
        ProjectInfo projectInfo = new ProjectInfo(infoService);
        UserForm userForm = new UserForm();
        LoginView loginView = new LoginView();
        VerticalLayout leftSide = new VerticalLayout(loginView, projectInfo);
        leftSide.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(userForm, loginView, projectInfo);
        setSizeFull();
    }

}
