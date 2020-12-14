package com.bankfrontend.uri;

import com.bankfrontend.AppStatics;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class CreditURL {

    public URI getForUserCreditsURI(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/credits")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getCreditOptionsURI(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/userOptions")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI deleteCreditURI(Long creditId) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/credit")
                .queryParam("creditId", creditId)
                .build().encode().toUri();
    }

    public URI createNewCreditURI(Long userId, Long accountId, Double quote, Integer days) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/credit")
                .queryParam("userId", userId)
                .queryParam("accountId", accountId)
                .queryParam("quote", quote)
                .queryParam("days", days)
                .build().encode().toUri();
    }

    public URI getInterestForUserURI(Long userId, int days) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/credit/interest")
                .queryParam("userId", userId)
                .queryParam("days", days)
                .build().encode().toUri();
    }

}
