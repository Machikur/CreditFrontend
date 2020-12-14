package com.bankfrontend.service;

import com.bankfrontend.domain.Payment;
import com.bankfrontend.domain.PaymentType;
import com.bankfrontend.uri.PaymentURL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PaymentURL paymentURL;

    @Mock
    private UserService userService;

    @Test
    public void getUserPaymentsTest() {
        //given
        Payment one = new Payment(1L, 2L, BigDecimal.TEN, false);
        Payment two = new Payment(1L, 4L, BigDecimal.TEN, false);
        Payment[] payments = {one, two};
        when(restTemplate.getForObject(any(), any())).thenReturn(payments);

        //when
        List<Payment> paymentAccountList = paymentService.getUserPayments(PaymentType.ACCOUNT);
        List<Payment> paymentCreditList = paymentService.getUserPayments(PaymentType.CREDIT);

        //then
        Assert.assertEquals(2, paymentAccountList.size());
        Assert.assertEquals(0, paymentCreditList.size());
        Assert.assertEquals(1L, paymentAccountList.get(0).getAccountFromId().longValue());
        verify(restTemplate, times(2)).getForObject(any(), any());
    }

    @Test
    public void getAccountPaymentsTest() {
        //given
        Payment one = new Payment(1L, 2L, BigDecimal.TEN, false);
        Payment two = new Payment(1L, 4L, BigDecimal.TEN, false);
        Payment[] payments = {one, two};
        when(restTemplate.getForObject(any(), any())).thenReturn(payments);

        //when
        List<Payment> paymentAccountList = paymentService.getAccountPayments("2222", PaymentType.ACCOUNT);

        //then
        Assert.assertEquals(2, paymentAccountList.size());
        Assert.assertEquals(1L, paymentAccountList.get(0).getAccountFromId().longValue());
        verify(restTemplate, times(1)).getForObject(any(), any());
    }

}