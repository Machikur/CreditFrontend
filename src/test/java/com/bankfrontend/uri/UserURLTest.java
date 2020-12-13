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
public class UserURLTest {

    @Autowired
    private UserURL userURL;

    @Test
    public void getUriForLoadUserTest() {
        //given
        String name = "Jan";
        String password = "Kowalski";

        //when
        String result = userURL.getUriForLoadUser(name, password).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(name));
        Assert.assertTrue(result.contains(password));
    }

    @Test
    public void getUriForDeleteUserTest() {
        //given
        Long userId = 1L;

        //when
        String result = userURL.getUriForDeleteUser(userId).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(userId.toString()));
    }

    @Test
    public void getUriForUserCurrenciesTest() {
        //given
        Long userId = 1L;

        //when
        String result = userURL.getUriForUserCurrencies(userId).toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
        Assert.assertTrue(result.contains(userId.toString()));
    }

    @Test
    public void getUriForSaveOrUpdateUserTest() {
        //given & when
        String result = userURL.getUriForSaveOrUpdateUser().toString();

        //then
        Assert.assertTrue(StringUtils.isNotBlank(result));
    }

}