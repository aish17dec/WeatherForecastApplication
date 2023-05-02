package com.example.weatherforecastapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {

    private String locationName;
    private String country;
    private LocalDateTime localSunriseTime;
    private LocalDateTime localSunsetTime;
    private TemperatureInfo currentTemperature;
    private String weatherDescription;
    private double windSpeed;
}
