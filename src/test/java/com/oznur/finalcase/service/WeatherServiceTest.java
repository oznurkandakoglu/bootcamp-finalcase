package com.oznur.finalcase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    @Mock
    private  WeatherFeignService feignService;

    @InjectMocks
    private WeatherService weatherService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getWeather() {
    }
}