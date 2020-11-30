package com.bankfrontend.frontend.uri;

import com.bankfrontend.frontend.StaticsURLAndStrings;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UserURL {

    public URI getUriForLoadUser(String name, String password) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.USER_URL)
                .queryParam("name", name)
                .queryParam("password", password)
                .build().encode().toUri();
    }

    public URI getUriForSaveOrUpdateUser() {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.USER_URL)
                .build().encode().toUri();
    }

    public URI getUriForDeleteUser(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.USER_URL)
                .queryParam("userId", userId)
                .build().encode().toUri();
    }


    public URI getUriForUserCurrencies(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(StaticsURLAndStrings.USER_URL + "Currencies")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }
}
