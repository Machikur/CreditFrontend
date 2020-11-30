package com.bankfrontend.frontend;

import org.springframework.web.client.RestTemplate;

public class ConfigurationProject {

    private static RestTemplate restTemplate;

    private ConfigurationProject() {
    }

    public static RestTemplate getInstanceOfRestTemplate() {
        if (restTemplate == null) {
            synchronized (RestTemplate.class) {
                if (restTemplate == null) {
                    restTemplate = new RestTemplate();
                }
            }
        }
        return restTemplate;
    }
}
