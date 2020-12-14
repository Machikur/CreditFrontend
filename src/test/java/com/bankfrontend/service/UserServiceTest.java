package com.bankfrontend.service;

import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {

    @Test
    public void getInstanceTest() {
        //given
        UserService userService2 = UserService.getInstance();
        UserService userService1 = UserService.getInstance();

        //when & then
        Assert.assertEquals(userService2, userService1);
    }

}