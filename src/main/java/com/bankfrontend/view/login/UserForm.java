package com.bankfrontend.view.login;

import com.bankfrontend.domain.User;
import com.bankfrontend.popup.PopUp;
import com.bankfrontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {

    private final TextField name = new TextField("Login");
    private final PasswordField password = new PasswordField("Hasło");
    private final TextField mailAddress = new TextField("Email");
    private final NumberField monthlyEarnings = new NumberField("Miesięczne zarobki");
    private final UserService userService = UserService.getInstance();

    public UserForm() {
        Button save = new Button("Zapisz użytkownika", b -> {
            User user = new User(name.getValue(), password.getValue(), mailAddress.getValue(), monthlyEarnings.getValue());
            boolean result = userService.saveUser(user);
            if (result) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                userService.loadUser(user.getName(), user.getPassword());
                UI.getCurrent().navigate("bank");
            } else {
                PopUp.throwPopUp("Wpisano nieprawidłowe dane");
            }
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Text title = new Text("Założ nowe konto");
        add(title, name, password, mailAddress, monthlyEarnings, save);
        Binder<User> userBinder = new Binder<>(User.class);
        userBinder.bindInstanceFields(this);
    }

}
