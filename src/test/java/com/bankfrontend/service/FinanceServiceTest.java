package com.bankfrontend.service;

import com.bankfrontend.domain.FinanceData;
import com.bankfrontend.uri.FinanceURL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FinanceServiceTest {

    @InjectMocks
    private FinanceService financeService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private FinanceURL financeURL;

    @Test
    public void getFinanceDataTest() {
        //given
        FinanceData[] financeData = {new FinanceData("TSL", 12.0, "12", "TeslaCompany")};
        when(restTemplate.getForObject(any(), any())).thenReturn(financeData);

        //when
        List<FinanceData> result = financeService.getFinanceData();

        //then
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("TSL", result.get(0).getTicker());
        Assert.assertEquals(12.0, result.get(0).getPrice(), 0.1);
        Assert.assertEquals("12", result.get(0).getChangesPercentage());
        Assert.assertEquals("TeslaCompany", result.get(0).getCompanyName());
    }

    @Test
    public void getCompanyNamesTest() {
        //given
        FinanceData[] financeData = {new FinanceData("TSL", 12.0, "12", "TeslaCompany")};
        when(restTemplate.getForObject(any(), any())).thenReturn(financeData);

        //when
        List<String> result = financeService.getCompanyNames();

        //then
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("TeslaCompany", result.get(0));
    }

}