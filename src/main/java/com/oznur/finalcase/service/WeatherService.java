package com.oznur.finalcase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oznur.finalcase.model.Weather;
import com.oznur.finalcase.model.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.DataInput;

@Service
public class WeatherService {

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @Value("${openweathermap.units}")
    private String units;
    private final WeatherFeignService feignService;

    public WeatherService(WeatherFeignService feignService) {
        this.feignService = feignService;
    }

    public WeatherDTO getWeather(String cityName) {

        return feignService.getWeather(cityName,apiKey,units);
    }

}
