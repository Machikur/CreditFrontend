package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.domain.Currency;
import com.bankfrontend.frontend.domain.Status;
import com.bankfrontend.frontend.domain.User;
import com.bankfrontend.frontend.uri.UserURL;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static Long userId = 48L;
    private static UserService userService;
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();
    private final UserURL userURL = new UserURL();
    private String userName = "Marcin";
    private Status userStatus = Status.VIP;
    private String userEmail = "Email@gmail.com";
    private Double monthlyEarnings = 2000.0;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null)
                    userService = new UserService();
            }
        }
        return new UserService();
    }

    public static Long getUserId() {
        return userId;
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

    public String getUserName() {
        return userName;
    }

    public Status getUserStatus() {
        return userStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Double getMonthlyEarnings() {
        return monthlyEarnings;
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
