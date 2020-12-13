package com.bankfrontend.service;

import com.bankfrontend.StaticsURLAndStrings;
import com.bankfrontend.domain.info.Info;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@UIScope
@SpringComponent
public class InfoService {

    private final RestTemplate restTemplate;

    @Autowired
    public InfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Info getProjectAndAdminInfo() {
        return restTemplate.getForObject(StaticsURLAndStrings.PROJECT_INFO, Info.class);
    }

}
