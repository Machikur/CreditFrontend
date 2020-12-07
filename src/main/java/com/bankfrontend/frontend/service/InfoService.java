package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.StaticsURLAndStrings;
import com.bankfrontend.frontend.domain.info.Info;
import org.springframework.web.client.RestTemplate;

public class InfoService {
    private static InfoService infoService;
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();

    private InfoService() {
    }

    public static InfoService getInstance() {
        if (infoService == null) {
            synchronized (InfoService.class) {
                if (infoService == null) {
                    infoService = new InfoService();
                }
            }
        }
        return infoService;
    }

    public Info getProjectAndAdminInfo() {
        return restTemplate.getForObject(StaticsURLAndStrings.PROJECT_INFO, Info.class);
    }

}
