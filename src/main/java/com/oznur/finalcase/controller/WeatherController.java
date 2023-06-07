package com.oznur.finalcase.controller;

import com.oznur.finalcase.exception.CityNotFoundException;
import com.oznur.finalcase.kafka.producer.KafkaProducerService;
import com.oznur.finalcase.model.WeatherDTO;
import com.oznur.finalcase.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@Slf4j
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/city/{cityName}")
    //@Secured("USER")
    @PreAuthorize("hasAnyRole('USER')")
    public WeatherDTO getFromController(@PathVariable String cityName) {
        try{
            kafkaProducerService.sendMessage("Get from controller method called!", "infoLogs");
            return weatherService.getWeather(cityName);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("City not found!", "errorLogs");
            throw new CityNotFoundException("City not found!");
        }

    }

}
