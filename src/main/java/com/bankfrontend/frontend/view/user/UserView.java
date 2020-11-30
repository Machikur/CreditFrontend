package com.bankfrontend.frontend.view.user;

import com.bankfrontend.frontend.domain.User;
import com.bankfrontend.frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestClientException;


@Route("user")
public class UserView extends HorizontalLayout {
    private final UserService userService = UserService.getInstance();
    private VerticalLayout updateForm;
    private Text answer = new Text("");

    public UserView() {
        setVertical();
        updateForm.setSizeFull();
        updateForm.setAlignItems(Alignment.CENTER);
        add(updateForm);
        setSizeFull();
    }

    public void setVertical() {

        TextField name = new TextField("Nazwa Użytkownika");
        name.setValue(userService.getUserName());

        PasswordField password = new PasswordField("Nowe Hasło");

        PasswordField passwordRepeat = new PasswordField("Powtórz nowe hasło");

        EmailField emailField = new EmailField("Email");
        emailField.setValue(userService.getUserEmail());

        NumberField monthlyEarnings = new NumberField("Zarobki");
        monthlyEarnings.setValue(userService.getMonthlyEarnings());

        Button submit = new Button("Zapisz Dane", event -> {
            if (password.getValue().equals(passwordRepeat.getValue())) {
                User user = new User(UserService.getUserId(),
                        name.getValue(),
                        password.getValue(),
                        emailField.getValue(),
                        null,
                        null,
                        monthlyEarnings.getValue(),
                        null
                        , null);
                try {
                    userService.updateUser(user);
                    userService.loadUser(user.getName(), user.getPassword());
                    Thread.sleep(2000);
                    answer.setText("Użytkownik został zaaktualizowany");
                    setVertical();
                } catch (RestClientException | InterruptedException s) {
                    answer.setText("Cos poszło nie tak, spróbuj jeszcze raz");
                }
            } else {
                answer.setText("Wprowadzone hasła nie są zgodne");
            }
        });

        updateForm = new VerticalLayout(name, password, passwordRepeat, emailField, monthlyEarnings, submit, answer, getBottomButtons());
    }

    private HorizontalLayout getBottomButtons() {
        Button delete = new Button("Usuń użytkownika", event -> {
            try {
                userService.deleteUser();
                UI.getCurrent().navigate("login");
            } catch (RestClientException s) {
                answer.setText("Użytkownik nie może być usunięty dopóki nie wyczyści kont i nie spłaci kredytów");
            }
        });
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        Button back = new Button("Wróć do menu", b -> UI.getCurrent().navigate("bank"));
        return new HorizontalLayout(back, delete);
    }

}
