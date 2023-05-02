# Weather Foreast Application

This application provides current weather forecast information. It makes use of Openweathermap API to get the latutide and longitude based on user provided pincode and country code. Based on this information it provides current weather details to the user along with local sunrise and sunset time.

This API is available on localhost:8081 when run on local machine. Also, mysql password and free API key from Openweathermap needs to be updated in application.properties.

For example to fetch current weather details of city Mysuru, Karnataka, India below will be the API call and sample response structure : 
``` 
http://localhost:8081/weather/current?pinCode=570001&country=IN 
```

Example Response : 
```
{
    "locationName": "Mysore",
    "country": "IN",
    "localSunriseTime": "2023-05-02T06:03:38",
    "localSunsetTime": "2023-05-02T18:37:45",
    "currentTemperature": {
        "feelsLike": 303.35,
        "minTemp": 302.17,
        "maxTemp": 302.17,
        "pressure": 1009.0,
        "humidity": 54.0
    },
    "weatherDescription": "broken clouds",
    "windSpeed": 1.09
}
```
