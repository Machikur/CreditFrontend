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
public class FinanceURLTest {

    @Autowired
    private FinanceURL financeURL;

    @Test
    public void getFinanceUrlTest() {
        //given & when
        String result = financeURL.getFinanceUrl().toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
    }

}