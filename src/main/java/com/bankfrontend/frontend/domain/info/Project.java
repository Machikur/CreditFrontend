package com.bankfrontend.frontend.domain.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("version")
    private String version;

    @JsonProperty("date")
    private String date;

    @JsonProperty("madefor")
    private String madeFor;
}
