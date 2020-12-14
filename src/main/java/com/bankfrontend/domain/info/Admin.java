package com.bankfrontend.domain.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @JsonProperty("name")
    private String name;

    @JsonProperty("mail")
    private String mail;
}
