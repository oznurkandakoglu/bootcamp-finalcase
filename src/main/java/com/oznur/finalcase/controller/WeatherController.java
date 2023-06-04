package com.oznur.finalcase.controller;

import com.oznur.finalcase.model.Weather;
import com.oznur.finalcase.model.WeatherDTO;
import com.oznur.finalcase.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/city/{cityName}")
    //@Secured("USER")
    @PreAuthorize("hasAnyRole('USER')")
    public WeatherDTO getFromController(@PathVariable String cityName) {
        log.info(cityName);
        log.error(cityName);
        log.warn(cityName);
        return weatherService.getWeather(cityName);
    }

}
