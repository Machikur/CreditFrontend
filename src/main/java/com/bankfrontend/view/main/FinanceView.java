package com.bankfrontend.view.main;

import com.bankfrontend.domain.FinanceData;
import com.bankfrontend.service.FinanceService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class FinanceView extends VerticalLayout {
    private final FinanceService financeService;
    private final TextField companyName = new TextField("Nazwa firmy");
    private final NumberField price = new NumberField("Cena akcji");
    private final TextField ticker = new TextField("Symbol");
    private final TextField changesPercentage = new TextField("Zmiany procentowe");
    private final List<FinanceData> financeDataList;

    public FinanceView(FinanceService financeService) {
        this.financeService = financeService;
        this.financeDataList = financeService.getFinanceData();
        companyName.setReadOnly(true);
        price.setReadOnly(true);
        ticker.setReadOnly(true);
        changesPercentage.setReadOnly(true);
        Text title = new Text("Przegląd najbardziej dynamicznych aukcji na giełdzie");
        add(title, getCompanies(), ticker, companyName, changesPercentage, price);
    }

    public ComboBox<String> getCompanies() {
        List<String> companyNames = financeService.getCompanyNames();
        ComboBox<String> comboBox = new ComboBox<>("Wybierz Firmę");
        comboBox.setItems(companyNames);
        comboBox.addValueChangeListener(event -> setCompanyDetails(comboBox.getValue()));
        comboBox.setValue(companyNames.get(0));
        return comboBox;
    }

    public void setCompanyDetails(String name) {
        FinanceData financeData = getCompanyData(name);
        companyName.setValue(financeData.getCompanyName());
        price.setValue(financeData.getPrice());
        ticker.setValue(financeData.getTicker());
        changesPercentage.setValue(financeData.getChangesPercentage());
    }

    public FinanceData getCompanyData(String companyName) {
        return financeDataList.stream()
                .filter(a -> a.getCompanyName().equals(companyName))
                .findAny()
                .orElse(null);
    }

}
