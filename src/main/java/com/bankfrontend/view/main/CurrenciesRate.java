package com.bankfrontend.view.main;

import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.domain.currency.Rates;
import com.bankfrontend.service.CurrencyService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.time.LocalDate;
import java.util.Arrays;

public class CurrenciesRate extends VerticalLayout {

    private final CurrencyService currencyService;

    public CurrenciesRate(CurrencyService currencyService) {
        this.currencyService = currencyService;

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
            Rates rates = this.currencyService.getCurrencies(currency).getRates();
            pln.setValue(rates.getPLN());
            gbp.setValue(rates.getGBP());
            usd.setValue(rates.getUSD());
            eur.setValue(rates.getEUR());
        });

        currencyComboBox.setValue(Currency.PLN);
        setSpacing(false);
        add(title, currencyComboBox);
        add(new HorizontalLayout(pln, eur));
        add(new HorizontalLayout(gbp, usd));
    }

}
