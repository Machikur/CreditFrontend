package com.bankfrontend.view.login;

import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class LoginView extends VerticalLayout {

    private final UserService userService = UserService.getInstance();

    public LoginView() {
        Text text = new Text("Zaloguj się");
        TextField userName = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        Button loadButton = new Button("Login", b -> {
            if (userService.loadUser(userName.getValue(), password.getValue())) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                UI.getCurrent().navigate("bank");
            } else {
                PopUp.throwPopUp("Nie znaleziono Użytkownika");
            }
        });
        add(text, userName, password, loadButton);
    }

}
