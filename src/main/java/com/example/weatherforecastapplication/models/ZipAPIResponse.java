package com.example.weatherforecastapplication.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipAPIResponse {
    private String zip;
    private String name;
    private double lat;
    private double lon;
    private String country;

    @Override
    public String toString() {
        return "ZipAPIResponse{" +
                "zip='" + zip + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", country='" + country + '\'' +
                '}';
    }
}
