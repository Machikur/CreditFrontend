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
public class CreditURLTest {

    @Autowired
    private CreditURL creditURL;

    @Test
    public void getForUserCreditsURITest() {
        //given
        Long userId = 1L;

        //when
        String result = creditURL.getForUserCreditsURI(userId).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(userId.toString()));
    }

    @Test
    public void getCreditOptionsURITest() {
        //given
        Long userId = 1L;

        //when
        String result = creditURL.getCreditOptionsURI(userId).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(userId.toString()));
    }

    @Test
    public void deleteCreditURITest() {
        //given
        Long creditId = 1L;

        //when
        String result = creditURL.deleteCreditURI(creditId).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(creditId.toString()));
    }

    @Test
    public void getInterestForUserURITest() {
        //given
        Long creditId = 1L;
        int days = 7;

        //when
        String result = creditURL.getInterestForUserURI(creditId, days).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(creditId.toString()));
        Assert.assertTrue(result.contains(Integer.toString(days)));
    }

    @Test
    public void createNewCreditURITest() {
        //given
        Long creditId = 1L;
        Long userId = 1L;
        Double quote = 20.0;
        Integer days = 7;

        //when
        String result = creditURL.createNewCreditURI(userId, creditId, quote, days).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(creditId.toString()));
        Assert.assertTrue(result.contains(userId.toString()));
        Assert.assertTrue(result.contains(quote.toString()));
        Assert.assertTrue(result.contains(days.toString()));
    }

}