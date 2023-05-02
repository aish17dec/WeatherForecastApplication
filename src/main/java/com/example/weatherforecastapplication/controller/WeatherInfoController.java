package com.example.weatherforecastapplication.controller;

import com.example.weatherforecastapplication.exception.WeatherInfoException;
import com.example.weatherforecastapplication.models.WeatherInfo;
import com.example.weatherforecastapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/weather")
public class WeatherInfoController {

    @Autowired
    WeatherService weatherService;

    @GetMapping(path = "/current")
    public ResponseEntity<WeatherInfo> getCurrentWeatherInfo(@RequestParam Integer pinCode, @RequestParam String country) throws WeatherInfoException {
        WeatherInfo weatherInfo = null;
        try{
            weatherInfo = weatherService.getCurrentWeatherInfo(pinCode,country);
        }catch(WeatherInfoException e){
            throw e;
        }catch(Exception e){
            throw new WeatherInfoException(e.getMessage());
        }

        return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
    }
}
