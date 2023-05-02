package com.example.weatherforecastapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureInfo {

    private double feelsLike;
    private double minTemp;
    private double maxTemp;
    private double pressure;
    private double humidity;

}
