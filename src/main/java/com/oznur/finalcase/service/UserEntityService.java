package com.oznur.finalcase.service;

import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.general.BaseEntityService;
import com.oznur.finalcase.mapper.UserMapper;
import com.oznur.finalcase.model.WeatherDTO;
import com.oznur.finalcase.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {

    private final UserRepository userRepository;
    private final WeatherService weatherService;

    public UserEntityService(UserRepository repository, UserRepository userRepository, WeatherService weatherService) {
        super(repository);
        this.userRepository = userRepository;
        this.weatherService = weatherService;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }

    public void delete(UserDeleteRequest userDeleteRequest){
        userRepository.delete(userRepository.findByUsername(userDeleteRequest.getUsername()).orElseThrow());
    }
    public UserDTO update(Long id, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userUpdateRequest.getUsername());
        user.setPassword(userUpdateRequest.getPassword());
        userRepository.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }
    public Map<String, WeatherDTO> getUsersSavedCitiesWeatherDTO(String username) {
        User user = findByUsername(username);
        List<String> cities = user.getSavedCities();
        Map<String, WeatherDTO> citiesData = new HashMap<>();

        for (String city : cities) {
            citiesData.put(city, weatherService.getWeather(city));
        }
        return citiesData;
    }

    public User addSavedCityToUser(String username, String city) {
        User user = findByUsername(username);
        List<String> cities = user.getSavedCities();
        if (cities == null) {
            cities = new ArrayList<String>();
        }
        cities.add(city);
        user.setSavedCities(cities);
        userRepository.save(user);
        return user;
    }

    public User deleteSavedCityFromUser(String username, String city) {
        User user = findByUsername(username);
        List<String> cities = user.getSavedCities();
        cities.remove(city);
        user.setSavedCities(cities);
        userRepository.save(user);
        return user;
    }
}
