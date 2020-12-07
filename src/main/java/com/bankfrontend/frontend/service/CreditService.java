package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.domain.Credit;
import com.bankfrontend.frontend.domain.CreditOptions;
import com.bankfrontend.frontend.domain.CreditType;
import com.bankfrontend.frontend.domain.currency.Currency;
import com.bankfrontend.frontend.uri.CreditURL;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreditService {
    private static CreditService creditService;
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();
    private final CreditURL creditURL = new CreditURL();

    private CreditService() {
    }

    public static CreditService getInstance() {
        if (creditService == null) {
            synchronized (CreditService.class) {
                if (creditService == null) {
                    creditService = new CreditService();
                }
            }
        }
        return creditService;
    }

    public Double getInterestForUser(int days) {
        return restTemplate.getForObject(creditURL.getInterestForUserURI(UserService.getUserId(), days), Double.class);
    }

    public List<Credit> getUserCredits() {
        Credit[] credits = restTemplate.getForObject(creditURL.getForUserCreditsURI(UserService.getUserId()), Credit[].class);
        if (credits == null || credits.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(credits);
    }

    public List<Long> getUserCreditsIds() {
        return getUserCredits().stream()
                .map(Credit::getCreditId)
                .collect(Collectors.toList());
    }

    public CreditOptions getUserCreditOptions() {
        return restTemplate.getForObject(creditURL.getCreditOptionsURI(UserService.getUserId()), CreditOptions.class);
    }

    public List<Integer> getAvailableCreditDays() {
        return getUserCreditOptions().getAvailableCreditTypes().stream()
                .map(CreditType::getDays)
                .collect(Collectors.toList());
    }

    public void makeCredit(Long userId, Long accountId,
                           Double quote, Integer days) {
        restTemplate.postForObject(creditURL.createNewCreditURL(userId, accountId, quote, days), null, Credit.class);
    }

    public void deleteCredit(Long creditId) {
        restTemplate.delete(creditURL.deleteCreditURI(creditId));
    }

    public Double getUserIndebtedness(Currency currency) {
        List<Credit> credits = getUserCredits();
        return credits.stream()
                .filter(c -> c.getCurrency().equals(currency))
                .mapToDouble(cr -> cr.getAmountToPay().doubleValue())
                .sum();
    }

}
