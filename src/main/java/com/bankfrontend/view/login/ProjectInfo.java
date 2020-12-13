package com.bankfrontend.view.login;

import com.bankfrontend.domain.info.Info;
import com.bankfrontend.service.InfoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class ProjectInfo extends HorizontalLayout {

    public ProjectInfo(InfoService infoService) {
        Info info = infoService.getProjectAndAdminInfo();
        TextField adminMail = new TextField("Kontakt", info.getAdmin().getMail(), "");
        TextField adminName = new TextField("Twórca", info.getAdmin().getName(), "");
        TextField projectName = new TextField("Nazwa projektu", info.getProject().getName(), "");
        TextField projectDesc = new TextField("Opis", info.getProject().getDescription(), "");
        TextField projectVersion = new TextField("Wersja", info.getProject().getVersion(), "");
        TextField projectDate = new TextField("Data ostatniej aktualizacji", info.getProject().getDate(), "");
        TextField projectMadeFor = new TextField("Stworzono dla", info.getProject().getMadeFor(), "");
        VerticalLayout left = new VerticalLayout(adminName, adminMail, projectName, projectDesc);
        VerticalLayout right = new VerticalLayout(projectVersion, projectDate, projectMadeFor);
        add(left, right);
        this.getChildren()
                .flatMap(Component::getChildren)
                .filter(component -> component instanceof TextField)
                .forEach(textField -> ((TextField) textField).setReadOnly(true));
        setSizeFull();
    }

}
