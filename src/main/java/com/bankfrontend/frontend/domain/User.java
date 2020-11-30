package com.bankfrontend.frontend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public User(String name, String password, String mailAddress, Double monthlyEarnings) {
        this.name = name;
        this.password = password;
        this.mailAddress = mailAddress;
        this.monthlyEarnings = monthlyEarnings;
    }

}
