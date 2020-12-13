package com.bankfrontend.service;

import com.bankfrontend.domain.Credit;
import com.bankfrontend.domain.CreditOptions;
import com.bankfrontend.domain.CreditType;
import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.uri.CreditURL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreditServiceTest {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CreditURL creditURL;

    @Mock
    private UserService userService;


    @Test
    public void getInterestForUserTest() {
        //given
        int days = 7;
        when(restTemplate.getForObject(any(), any())).thenReturn(20.0);

        //when
        Double result = creditService.getInterestForUser(days);

        //then
        Assert.assertTrue(Objects.nonNull(result));
        Assert.assertEquals(20.0, result, 0.1);
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

    @Test
    public void getUserCreditsTest() {
        //given
        Credit[] creditArray = {new Credit(), new Credit()};
        when(restTemplate.getForObject(any(), any())).thenReturn(creditArray);

        //when
        List<Credit> result = creditService.getUserCredits();

        //then
        Assert.assertTrue(Objects.nonNull(result));
        Assert.assertEquals(2, result.size());
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

    @Test
    public void getUserCreditsIdsTest() {
        //given
        Credit one = new Credit(1L, 2L, BigDecimal.TEN, Currency.PLN, BigDecimal.ZERO,
                LocalDate.now(), LocalDate.now().plusDays(7), false, new ArrayList<>());
        Credit[] creditArray = {one};
        when(restTemplate.getForObject(any(), any())).thenReturn(creditArray);

        //when
        List<Long> result = creditService.getUserCreditsIds();

        //then
        Assert.assertTrue(Objects.nonNull(result));
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(1L, result.get(0).longValue());
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

    @Test
    public void getUserCreditOptionsTest() {
        //given
        CreditOptions creditOptions = new CreditOptions(BigDecimal.valueOf(1000), Arrays.asList(CreditType.values()));

        when(restTemplate.getForObject(any(), any())).thenReturn(creditOptions);

        //when
        CreditOptions result = creditService.getUserCreditOptions();

        //then
        Assert.assertTrue(Objects.nonNull(result));
        Assert.assertEquals(4, result.getAvailableCreditTypes().size());
        Assert.assertEquals(BigDecimal.valueOf(1000), result.getMaxQuote());
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

    @Test
    public void getAvailableCreditDaysTest() {
        //given
        CreditOptions creditOptions = new CreditOptions(BigDecimal.valueOf(1000), Arrays.asList(CreditType.values()));
        when(restTemplate.getForObject(any(), any())).thenReturn(creditOptions);

        //when
        List<Integer> result = creditService.getAvailableCreditDays();

        //then
        Assert.assertTrue(Objects.nonNull(result));
        Assert.assertEquals(4, result.size());
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

    @Test
    public void makeCreditTest() {
        //given
        Long userId = 1L;
        Long accountId = 2L;
        Double quote = 20.0;
        Integer days = 7;

        //when
        creditService.makeCredit(userId, accountId, quote, days);

        //then
        verify(restTemplate, times(1)).postForObject(any(), any(), any());
    }

    @Test
    public void deleteCreditTest() {
        //given
        Long creditId = 1L;

        //when
        creditService.deleteCredit(creditId);

        //then
        verify(restTemplate, times(1)).delete(any());
    }

    @Test
    public void getUserIndebtednessTest() {
        //given
        Currency currency = Currency.PLN;
        Credit one = new Credit(1L, 2L, BigDecimal.TEN, Currency.PLN, BigDecimal.ZERO,
                LocalDate.now(), LocalDate.now().plusDays(7), false, new ArrayList<>());
        Credit[] creditArray = {one};
        when(restTemplate.getForObject(any(), any())).thenReturn(creditArray);

        //when
        Double result = creditService.getUserIndebtedness(currency);

        //then
        Assert.assertEquals(10.0, result, 0.1);
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

}