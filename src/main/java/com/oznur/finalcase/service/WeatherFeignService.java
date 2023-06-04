package com.oznur.finalcase.service;

import com.oznur.finalcase.model.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="weatherapp", url= "https://api.openweathermap.org/data/2.5/forecast")
public interface WeatherFeignService {

    @GetMapping
    WeatherDTO getWeather(@RequestParam String q, @RequestParam String appid, @RequestParam String units);
}
