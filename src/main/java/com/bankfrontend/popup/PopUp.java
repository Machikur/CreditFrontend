package com.bankfrontend.popup;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;

public class PopUp extends Dialog {
    private static final PopUp popUp;
    private static final Text textField = new Text("");

    static {
        popUp = new PopUp(textField);
    }

    private PopUp(Component component) {
        add(component);
    }

    public static void throwPopUp(String text) {
        textField.setText(text);
        popUp.open();
    }

    public static void throwErrorPopUp() {
        textField.setText("Coś poszło nie tak, spróbuj jeszcze raz");
        popUp.open();
    }

}
