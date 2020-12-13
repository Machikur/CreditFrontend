package com.bankfrontend.service;

import com.bankfrontend.ConfigurationProject;
import com.bankfrontend.domain.Status;
import com.bankfrontend.domain.User;
import com.bankfrontend.domain.currency.Currency;
import com.bankfrontend.uri.UserURL;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Getter
public class UserService {

    private final RestTemplate restTemplate=new RestTemplate();
    private static volatile UserService userService;
    private final UserURL userURL = new UserURL();
    private Long userId;
    private String userName;
    private Status userStatus;
    private String userEmail;
    private Double monthlyEarnings;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null)
                    userService = new UserService();
            }
        }
        return userService;
    }


    public boolean saveUser(User user) {
        User optionalUser;
        try {
            optionalUser = restTemplate.postForObject(userURL.getUriForSaveOrUpdateUser(), user, User.class);
        } catch (Exception s) {
            System.out.println(s.getMessage());
            return false;
        }
        return optionalUser != null;
    }


    public boolean loadUser(String username, String password) {
        User optionalUser;
        try {
            optionalUser = restTemplate.getForObject(userURL.getUriForLoadUser(username, password), User.class);
        } catch (Exception s) {
            System.out.println(s.getMessage());
            return false;
        }
        if (optionalUser != null) {
            userId = optionalUser.getId();
            userName = optionalUser.getName();
            userStatus = optionalUser.getStatus();
            userEmail = optionalUser.getMailAddress();
            monthlyEarnings = optionalUser.getMonthlyEarnings();
            return true;
        }
        return false;
    }

    public void logout() {
        userId = null;
        userName = null;
        userStatus = null;
        userEmail = null;
        monthlyEarnings = null;
    }

    public void deleteUser() {
        restTemplate.delete(userURL.getUriForDeleteUser(userId));
    }

    public void updateUser(User user) {
        restTemplate.put(userURL.getUriForSaveOrUpdateUser(), user);
    }

    public List<Currency> getUserCurrencies() {
        Currency[] currencies = restTemplate.getForObject(userURL.getUriForUserCurrencies(userId), Currency[].class);
        if (Objects.nonNull(currencies)) {
            return Arrays.asList(currencies);
        } else {
            return new ArrayList<>();
        }
    }

}
