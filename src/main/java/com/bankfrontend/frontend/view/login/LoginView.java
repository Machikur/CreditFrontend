package com.bankfrontend.frontend.view.login;

import com.bankfrontend.frontend.service.UserService;
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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UI.getCurrent().navigate("bank");
            } else {
                text.setText("Nie znaleziono Użytkownika");
            }
        });
        add(text, userName, password, loadButton);
        //setAlignItems(Alignment.CENTER);
    }

}
