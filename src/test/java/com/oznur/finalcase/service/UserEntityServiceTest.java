package com.oznur.finalcase.service;


import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  WeatherService weatherService;

    @InjectMocks
    private UserEntityService userEntityService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userEntityService = new UserEntityService(userRepository, userRepository, weatherService);
    }
    @Test
    void shouldFindByUsername() {
    }

    @Test
    void shouldDelete() {
        User user = new User();
        userEntityService.delete(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldGetUsersSavedCitiesWeatherDTO() {
    }

    @Test
    void shouldAddSavedCityToUser() {
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User updatedUser = userEntityService.addSavedCityToUser("login", "city");

        verify(userRepository, times(1)).findByUsername("login");
        verify(userRepository, times(1)).save(user);
        assertEquals(List.of("city"), updatedUser.getSavedCities());
    }

    @Test
    void shouldDeleteSavedCityFromUser() {
        // Arrange
        // Arrange
        String username = "username";
        String city = "city1";
        User user = new User();
        user.setSavedCities(List.of(city));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // Act
        User result = userEntityService.deleteSavedCityFromUser(username, city);

        // Assert
        assertEquals(new ArrayList<>(), result.getSavedCities());
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(user);
    }
}