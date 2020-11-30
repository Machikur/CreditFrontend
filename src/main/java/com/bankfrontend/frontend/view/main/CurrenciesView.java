package com.bankfrontend.frontend.view.main;

import com.bankfrontend.frontend.domain.Currency;
import com.bankfrontend.frontend.domain.Rates;
import com.bankfrontend.frontend.service.CurrencyService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;

import java.time.LocalDate;
import java.util.Arrays;

public class CurrenciesView extends HorizontalLayout {
    private final CurrencyService currencyService = CurrencyService.getInstance();

    public CurrenciesView() {
        add(getCurrenciesRates(), getCounterRate());
    }

    public VerticalLayout getCurrenciesRates() {
        VerticalLayout verticalLayout = new VerticalLayout();
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
        verticalLayout.add(title, currencyComboBox, pln, eur, gbp, usd);
        return verticalLayout;
    }

    public HorizontalLayout getCounterRate() {
        ComboBox<Currency> currencyFrom = getComboBox("Waluta do zmiany");
        ComboBox<Currency> currencyTo = getComboBox("Waluta po zmianie");

        NumberField rateTo = new NumberField("Kwota po zamianie");
        rateTo.setValue(0.0);
        rateTo.setReadOnly(true);

        NumberField rateFrom = new NumberField("Kwota do zamiany");
        rateFrom.setValue(0.0);

        Button countButton = new Button("przelicz", event -> countValue(currencyFrom, currencyTo, rateFrom, rateTo));
        Button changeButton = new Button("<>", event -> {
            Currency toChange = currencyFrom.getValue();
            currencyFrom.setValue(currencyTo.getValue());
            currencyTo.setValue(toChange);
        });

        rateFrom.addValueChangeListener(event -> countValue(currencyFrom, currencyTo, rateFrom, rateTo));
        currencyFrom.addValueChangeListener(event -> countValue(currencyFrom, currencyTo, rateFrom, rateTo));
        currencyTo.addValueChangeListener(event -> countValue(currencyFrom, currencyTo, rateFrom, rateTo));

        VerticalLayout leftLayout = new VerticalLayout(currencyFrom, rateFrom);
        VerticalLayout middleLayout = new VerticalLayout(new Text("Zamień waluty"), changeButton, countButton);
        VerticalLayout rightLayout = new VerticalLayout(currencyTo, rateTo);

        return new HorizontalLayout(leftLayout, middleLayout, rightLayout);

    }

    private void countValue(ComboBox<Currency> from, ComboBox<Currency> to, NumberField quoteField, NumberField result) {
        Currency curFrom = from.getValue();
        Currency curTo = to.getValue();
        Double quote = quoteField.getValue();
        result.setValue(currencyService.countQuote(curFrom.getDesc(), curTo.getDesc(), quote));
    }

    private ComboBox<Currency> getComboBox(String name) {
        ComboBox<Currency> comboBox = new ComboBox<>(name);
        comboBox.setItems(Currency.values());
        comboBox.setValue(Currency.PLN);
        return comboBox;
    }

}