package com.oznur.finalcase.service;


import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.model.WeatherDTO;
import com.oznur.finalcase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    }
    @Test
    void shouldFindByUsername() {
        // Arrange
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // Act
        User result = userEntityService.findByUsername("username");

        // Assert
        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test
    void shouldDelete() {
        // Arrange
        UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
        userDeleteRequest.setUsername("username");
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // Act
        userEntityService.delete(userDeleteRequest);

        // Assert
        verify(userRepository, times(1)).findByUsername("username");
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void shouldUpdate() {
        // Arrange
        Long id = 1L;
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setUsername("newUsername");
        User user = new User();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        // Act
        userEntityService.update(id, userUpdateRequest);

        // Assert
        assertEquals("newUsername", user.getUsername());
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void shouldGetUsersSavedCitiesWeatherDTO() {
        // Arrange
        String username = "username";
        User user = new User();
        user.setSavedCities(List.of("city1", "city2"));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        WeatherDTO weatherDTO1 = new WeatherDTO();
        WeatherDTO weatherDTO2 = new WeatherDTO();
        when(weatherService.getWeather("city1")).thenReturn(weatherDTO1);
        when(weatherService.getWeather("city2")).thenReturn(weatherDTO2);

        // Act
        Map<String, WeatherDTO> result = userEntityService.getUsersSavedCitiesWeatherDTO(username);

        // Assert
        Map<String, WeatherDTO> expectedData = new HashMap<>();
        expectedData.put("city1", weatherDTO1);
        expectedData.put("city2", weatherDTO2);

        assertEquals(expectedData, result);
        verify(userRepository, times(1)).findByUsername(username);
        verify(weatherService, times(1)).getWeather("city1");
        verify(weatherService, times(1)).getWeather("city2");
    }

    @Test
    void shouldAddSavedCityToUser() {
        // Arrange
        String username = "username";
        String city = "city1";
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // Act
        User result = userEntityService.addSavedCityToUser(username, city);

        // Assert
        List<String> expectedCities = new ArrayList<>();
        expectedCities.add(city);
        assertEquals(expectedCities, result.getSavedCities());
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldDeleteSavedCityFromUser() {
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
        verify(userRepository, times(1)).save(result); // Değişiklik: 'user' yerine 'result' kullanıldı
    }
}