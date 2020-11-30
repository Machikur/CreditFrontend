package com.bankfrontend.frontend.view.main;

import com.bankfrontend.frontend.domain.Currency;
import com.bankfrontend.frontend.domain.Rates;
import com.bankfrontend.frontend.service.CurrencyService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.time.LocalDate;
import java.util.Arrays;

public class CurrenciesRate extends VerticalLayout {

    private final CurrencyService currencyService = CurrencyService.getInstance();

    public CurrenciesRate() {

        ComboBox<Currency> currencyComboBox = new ComboBox<>("Waluta");
        Text title = new Text("Sprawdź kurs walut na dzień " + LocalDate.now());
        currencyComboBox.setItems(Currency.values());
        NumberField pln = new NumberField("PLN");
        NumberField eur = new NumberField("EUR");
        NumberField gbp = new NumberField("GBP");
        NumberField usd = new NumberField("USD");
        NumberField[] fields = {pln, usd, gbp, eur};
        Arrays.stream(fields).forEach(s -> s.setReadOnly(true));
        currencyComboBox.addValueChangeListener(s -> {
            Currency currency = currencyComboBox.getValue();
            Rates rates = currencyService.getCurrencies(currency.getDesc()).getRates();
            pln.setValue(rates.getPLN());
            gbp.setValue(rates.getGBP());
            usd.setValue(rates.getUSD());
            eur.setValue(rates.getEUR());
        });
        currencyComboBox.setValue(Currency.PLN);
        setSpacing(false);
        add(title, currencyComboBox, pln, eur, gbp, usd);
    }

}