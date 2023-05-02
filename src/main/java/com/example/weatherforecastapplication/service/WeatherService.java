package com.example.weatherforecastapplication.service;

import com.example.weatherforecastapplication.exception.WeatherInfoException;
import com.example.weatherforecastapplication.models.WeatherInfo;

public interface WeatherService {

    WeatherInfo getCurrentWeatherInfo(Integer pinCode, String countryCode) throws WeatherInfoException;
}
