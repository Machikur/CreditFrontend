package com.bankfrontend.frontend.uri;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UserURL {

    public URI getUriForLoadUser(String name, String password) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL+"/user")
                .queryParam("name", name)
                .queryParam("password", password)
                .build().encode().toUri();
    }

    public URI getUriForSaveOrUpdateUser() {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL+"/user")
                .build().encode().toUri();
    }

    public URI getUriForDeleteUser(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL+"/user")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getUriForUserCurrencies(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.BASE_URL+"/userCurrencies")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }
}
