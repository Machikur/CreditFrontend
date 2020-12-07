package com.bankfrontend.frontend.uri;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class FinanceURL {

    public URI getFinanceUrl() {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.FINANCE_BASE_URL)
                .queryParam("apikey", StaticsURLAndStrings.API_TOKEN)
                .build().encode().toUri();
    }

}
