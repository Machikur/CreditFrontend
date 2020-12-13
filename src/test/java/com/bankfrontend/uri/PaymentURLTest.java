package com.bankfrontend.uri;

import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentURLTest {

    @Autowired
    private PaymentURL paymentURL;

    @Test
    public void getUserPaymentsUriTest() {
        //given
        Long userId = 1L;

        //when
        String result = paymentURL.getUserPaymentsURL(userId).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(userId.toString()));
    }

    @Test
    public void getAccountPaymentsUriTest() {
        //given
        String accountNumber = "22222";

        //when
        String result = paymentURL.getAccountPaymentsURL(accountNumber).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(accountNumber));
    }

    @Test
    public void makePaymentURLTest() {
        //given
        long userId = 1L;
        int pinCode = 1234;

        //when
        String result = paymentURL.makePaymentURL(userId, pinCode).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(Long.toString(userId)));
        Assert.assertTrue(result.contains(Integer.toString(pinCode)));
    }

}