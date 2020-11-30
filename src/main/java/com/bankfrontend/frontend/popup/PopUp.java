package com.bankfrontend.frontend.popup;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;

public class PopUp extends Dialog {
    private static PopUp popUp;
    private static Text textField = new Text("");

    static {
        if (popUp == null) {
            synchronized (PopUp.class) {
                if (popUp == null) {
                    popUp = new PopUp(textField);
                }
            }
        }
    }

    private PopUp(Component... components) {
        super(components);
    }

    public static void throwPopUp(String text) {
        ;
        textField.setText(text);
        popUp.open();
    }

    public static void throwErrorPopUp() {
        textField.setText("Coś poszło nie tak, spróbuj jeszcze raz");
        popUp.open();
    }
}
