package com.oznur.finalcase.service;

import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.mapper.UserMapper;
import com.oznur.finalcase.model.WeatherDTO;
import com.oznur.finalcase.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserEntityServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private UserEntityService userEntityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindByUsername() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        User result = userEntityService.findByUsername("testUser");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    public void shouldFindByUsername_ThrowsException() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userEntityService.findByUsername("nonExistingUser"));
    }

    @Test
    public void shouldDelete() {
        UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
        userDeleteRequest.setUsername("testUser");

        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        userEntityService.delete(userDeleteRequest);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void shouldUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setUsername("newUsername");

        User user = new User();
        user.setId(1L);
        user.setUsername("oldUsername");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userEntityService.update(1L, userUpdateRequest);

        assertEquals("newUsername", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldGetUsersSavedCitiesWeatherDTO() {
        User user = new User();
        user.setUsername("testUser");
        user.setSavedCities(Arrays.asList("city1", "city2"));

        WeatherDTO weatherDTO1 = new WeatherDTO();
        WeatherDTO weatherDTO2 = new WeatherDTO();

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(weatherService.getWeather("city1")).thenReturn(weatherDTO1);
        when(weatherService.getWeather("city2")).thenReturn(weatherDTO2);

        Map<String, WeatherDTO> result = userEntityService.getUsersSavedCitiesWeatherDTO("testUser");

        assertEquals(2, result.size());
        assertEquals(weatherDTO1, result.get("city1"));
        assertEquals(weatherDTO2, result.get("city2"));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(weatherService, times(1)).getWeather("city1");
        verify(weatherService, times(1)).getWeather("city2");
    }

    @Test
    void shouldAddSavedCityToUser() {

        String username = "username";
        String city = "city1";
        User user = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User result = userEntityService.addSavedCityToUser(username, city);


        List<String> expectedCities = new ArrayList<>();
        expectedCities.add(city);
        assertEquals(expectedCities, result.getSavedCities());
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldDeleteSavedCityFromUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setSavedCities(Arrays.asList("city1", "city2"));

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userEntityService.deleteSavedCityFromUser("testUser", "city2");

        assertEquals(1, result.getSavedCities().size());
        assertEquals("city1", result.getSavedCities().get(0));

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(userRepository, times(1)).save(user);
    }
}
