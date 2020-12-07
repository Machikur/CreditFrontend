package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.domain.FinanceData;
import com.bankfrontend.frontend.uri.FinanceURL;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinanceService {

    private static FinanceService financeService;
    private final FinanceURL financeURL = new FinanceURL();
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();

    private FinanceService() {
    }

    public static FinanceService getInstance() {
        if (financeService == null) {
            synchronized (FinanceService.class) {
                if (financeService == null) {
                    financeService = new FinanceService();
                }
            }
        }
        return financeService;
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
