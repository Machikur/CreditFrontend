package com.bankfrontend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;

    private String name;

    private String password;

    private String mailAddress;

    private List<Account> accounts;

    private List<Credit> credits;

    private Double monthlyEarnings;

    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    private boolean enable;

    public User(String name, String password, String mailAddress, Double monthlyEarnings) {
        this.name = name;
        this.password = password;
        this.mailAddress = mailAddress;
        this.monthlyEarnings = monthlyEarnings;
    }

    public User(Long id, String name, String password, String mailAddress, Double monthlyEarnings) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mailAddress = mailAddress;
        this.monthlyEarnings = monthlyEarnings;
    }

}
