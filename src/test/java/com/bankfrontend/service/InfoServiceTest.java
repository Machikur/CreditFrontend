package com.bankfrontend.service;

import com.bankfrontend.domain.info.Admin;
import com.bankfrontend.domain.info.Info;
import com.bankfrontend.domain.info.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class InfoServiceTest {

    @InjectMocks
    private InfoService infoService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getProjectAndAdminInfoTest() {
        //given
        Info info = new Info(new Admin("Marcin", "mail@mail.com")
                , new Project("Credit", "CreditApp", "V1.0", "2.2", "Kodilla"));

        when(restTemplate.getForObject(anyString(), any())).thenReturn(info);

        //when
        Info result = infoService.getProjectAndAdminInfo();

        //then
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        Assert.assertNotNull(result);
        Assert.assertEquals("Marcin", result.getAdmin().getName());
        Assert.assertEquals("Kodilla", result.getProject().getMadeFor());
    }

}