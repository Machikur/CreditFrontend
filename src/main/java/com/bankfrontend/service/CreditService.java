package com.bankfrontend.service;

import com.bankfrontend.domain.Credit;
import com.bankfrontend.domain.CreditOptions;
import com.bankfrontend.domain.CreditType;
import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.uri.CreditURL;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class CreditService {

    private final RestTemplate restTemplate;
    private final CreditURL creditURL;
    private final UserService userService = UserService.getInstance();

    @Autowired
    public CreditService(RestTemplate restTemplate, CreditURL creditURL) {
        this.restTemplate = restTemplate;
        this.creditURL = creditURL;
    }

    public Double getInterestForUser(int days) {
        return restTemplate.getForObject(creditURL.getInterestForUserURI(userService.getUserId(), days), Double.class);
    }

    public List<Credit> getUserCredits() {
        Credit[] credits = restTemplate.getForObject(creditURL.getForUserCreditsURI(userService.getUserId()), Credit[].class);
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
        return restTemplate.getForObject(creditURL.getCreditOptionsURI(userService.getUserId()), CreditOptions.class);
    }

    public List<Integer> getAvailableCreditDays() {
        return getUserCreditOptions().getAvailableCreditTypes().stream()
                .map(CreditType::getDays)
                .collect(Collectors.toList());
    }

    public void makeCredit(Long userId, Long accountId,
                           Double quote, Integer days) {
        restTemplate.postForObject(creditURL.createNewCreditURI(userId, accountId, quote, days), null, Credit.class);
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
