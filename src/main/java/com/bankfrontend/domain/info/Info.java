package com.bankfrontend.domain.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @JsonProperty("admin")
    private Admin admin;

    @JsonProperty("project")
    private Project project;

}
