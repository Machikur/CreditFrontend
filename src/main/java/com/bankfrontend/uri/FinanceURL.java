package com.bankfrontend.uri;

import com.bankfrontend.StaticsURLAndStrings;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class FinanceURL {

    public URI getFinanceUrl() {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.FINANCE_BASE_URL)
                .queryParam("apikey", StaticsURLAndStrings.API_TOKEN)
                .build().encode().toUri();
    }

}
