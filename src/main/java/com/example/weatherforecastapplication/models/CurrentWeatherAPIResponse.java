package com.example.weatherforecastapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CurrentWeatherAPIResponse {

    private List<Weather> weather;
    private Coord coord;
    private String base;
    private String name;
    private Main main;
    private Wind wind;
    private Sys sys;
}
