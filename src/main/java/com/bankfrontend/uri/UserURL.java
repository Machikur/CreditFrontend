package com.bankfrontend.uri;

import com.bankfrontend.AppStatics;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class UserURL {

    public URI getUriForLoadUser(String name, String password) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/user")
                .queryParam("name", name)
                .queryParam("password", password)
                .build().encode().toUri();
    }

    public URI getUriForSaveOrUpdateUser() {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/user")
                .build().encode().toUri();
    }

    public URI getUriForDeleteUser(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/user")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

    public URI getUriForUserCurrencies(Long userId) {
        return UriComponentsBuilder.fromHttpUrl(AppStatics.BASE_URL + "/userCurrencies")
                .queryParam("userId", userId)
                .build().encode().toUri();
    }

}
