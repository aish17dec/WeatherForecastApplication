package com.example.weatherforecastapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
    /*
    *  "type": 1,
        "id": 9215,
        "country": "IN",
        "sunrise": 1682897404,
        "sunset": 1682942541
        * */

    private double type;
    private double id;
    private long sunrise;
    private long sunset;
    private String country;
}
