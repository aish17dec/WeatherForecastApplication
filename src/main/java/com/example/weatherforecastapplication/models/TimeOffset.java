package com.example.weatherforecastapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TimeOffset {
    private double offset;
    private String latitude;
    private String longitude;
    @JsonProperty("timezone_id")
    private String timezoneId;
    @JsonProperty("map_url")
    private String mapUrl;
    @JsonProperty("country_code")
    private String countryCode;
}
