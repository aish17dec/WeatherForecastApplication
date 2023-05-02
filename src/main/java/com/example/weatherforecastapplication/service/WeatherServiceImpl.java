package com.example.weatherforecastapplication.service;

import com.example.weatherforecastapplication.exception.WeatherInfoException;
import com.example.weatherforecastapplication.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${openWeatherApiKey}")
    private String apiKey;

    private final static String TIMEOFFSETURL = "https://api.wheretheiss.at/v1/coordinates/";

    @Override
    public WeatherInfo getCurrentWeatherInfo(Integer pinCode, String countryCode) throws WeatherInfoException {
        return getWeatherInformationFromAPI(pinCode,countryCode);
    }

    private WeatherInfo getWeatherInformationFromAPI(Integer pinCode, String countryCode) throws WeatherInfoException {
        WeatherInfo weatherInfo = new WeatherInfo();
        String zip = pinCode.toString().trim()+","+countryCode.trim();

        String url = "http://api.openweathermap.org/geo/1.0/zip?zip={zip}&appid={appid}";
        Map<String, String> params = new HashMap<>();
        params.put("zip",zip);
        params.put("appid",apiKey);
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ZipAPIResponse zipAPIResponse = restTemplate.getForObject(url, ZipAPIResponse.class,params);
            double latitude = zipAPIResponse.getLat();
            double longitude = zipAPIResponse.getLon();

            params.put("lat", String.valueOf(latitude));
            params.put("lon", String.valueOf(longitude));

            url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={appid}";
            CurrentWeatherAPIResponse currentWeatherAPIResponse= restTemplate.getForObject(url, CurrentWeatherAPIResponse.class,params);
            weatherInfo.setLocationName(currentWeatherAPIResponse.getName());
            weatherInfo.setCountry(currentWeatherAPIResponse.getSys().getCountry());

            Map<String,Integer> offsetDetails = getTimeOffset(latitude,longitude);

            LocalDateTime sunrise = LocalDateTime.ofEpochSecond(currentWeatherAPIResponse.getSys().getSunrise(), 0, ZoneOffset.ofHoursMinutes(offsetDetails.get("hours"),offsetDetails.get("minutes")));
            LocalDateTime sunset = LocalDateTime.ofEpochSecond(currentWeatherAPIResponse.getSys().getSunset(), 0, ZoneOffset.ofHoursMinutes(offsetDetails.get("hours"),offsetDetails.get("minutes")));

            weatherInfo.setLocalSunriseTime(sunrise);
            weatherInfo.setLocalSunsetTime(sunset);
            weatherInfo.setWeatherDescription(currentWeatherAPIResponse.getWeather().get(0).getDescription());
            TemperatureInfo temperatureInfo = new TemperatureInfo();
            temperatureInfo.setMaxTemp(currentWeatherAPIResponse.getMain().getTempMax());
            temperatureInfo.setMinTemp(currentWeatherAPIResponse.getMain().getTempMin());
            temperatureInfo.setPressure(currentWeatherAPIResponse.getMain().getPressure());
            temperatureInfo.setFeelsLike(currentWeatherAPIResponse.getMain().getFeelsLike());
            temperatureInfo.setHumidity(currentWeatherAPIResponse.getMain().getHumidity());

            weatherInfo.setCurrentTemperature(temperatureInfo);
            weatherInfo.setWindSpeed(currentWeatherAPIResponse.getWind().getSpeed());

        }catch(Exception e){
            throw new WeatherInfoException(e.getMessage());
        }

        return weatherInfo;
    }

    private Map<String,Integer> getTimeOffset(double lat, double lon) throws JsonProcessingException {
        Map<String,Integer> offsetDetails = new HashMap<>();
        String url = TIMEOFFSETURL+lat+","+lon;
        TimeOffset timeOffset = restTemplate.getForObject(url, TimeOffset.class);
        double offset = timeOffset.getOffset();

        int hours = (int) ((offset*60)/60);
        int minutes = (int) ((offset*60)%60);

        offsetDetails.put("hours",hours);
        offsetDetails.put("minutes",minutes);
        return offsetDetails;
    }

}
