package com.example.springBootFirst.service;

import com.example.springBootFirst.pojo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    public static final String apiKey = "edbvxfbgodgbodfbgsdvdsvff";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

   @Autowired
   private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
         String finalAPI = API.replace("CITY",city).replace("API_KEY",apiKey);
         ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
         WeatherResponse body = response.getBody();
         return body;
    }


}
