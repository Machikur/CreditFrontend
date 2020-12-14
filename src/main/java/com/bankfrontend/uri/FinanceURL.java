package com.bankfrontend.uri;

import com.bankfrontend.AppStatics;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class FinanceURL {

    public URI getFinanceUrl() {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.FINANCE_BASE_URL)
                .queryParam("apikey", AppStatics.API_TOKEN)
                .build().encode().toUri();
    }

}
