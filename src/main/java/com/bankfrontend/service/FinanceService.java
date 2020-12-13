package com.bankfrontend.service;

import com.bankfrontend.domain.FinanceData;
import com.bankfrontend.uri.FinanceURL;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class FinanceService {

    private final FinanceURL financeURL;
    private final RestTemplate restTemplate;

    @Autowired
    public FinanceService(FinanceURL financeURL, RestTemplate restTemplate) {
        this.financeURL = financeURL;
        this.restTemplate = restTemplate;
    }

    public List<FinanceData> getFinanceData() {
        FinanceData[] financeData = restTemplate.getForObject(financeURL.getFinanceUrl(), FinanceData[].class);
        return Objects.nonNull(financeData) ? Arrays.asList(financeData) : new ArrayList<>();
    }

    public List<String> getCompanyNames() {
        return getFinanceData().stream()
                .map(FinanceData::getCompanyName)
                .collect(Collectors.toList());
    }

}
