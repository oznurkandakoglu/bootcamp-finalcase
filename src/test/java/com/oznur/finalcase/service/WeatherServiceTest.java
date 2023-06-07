package com.oznur.finalcase.service;

import com.oznur.finalcase.model.WeatherDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherFeignService feignService;

    @InjectMocks
    private WeatherService weatherService;

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @Value("${openweathermap.units}")
    private String units;

    @Test
    public void getWeather_ShouldReturnWeatherDTO() {
        WeatherDTO weatherDTO = new WeatherDTO();
        when(feignService.getWeather("city", apiKey, units)).thenReturn(weatherDTO);
        WeatherDTO city = weatherService.getWeather("city");
        assertEquals(weatherDTO, city);

    }

}


