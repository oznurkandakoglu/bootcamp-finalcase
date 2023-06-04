package com.oznur.finalcase.controller.contract;

import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserRegisterRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.model.WeatherDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserControllerContract {

    //ResponseEntity<String> loginUser(UserDTO userDTO);

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    void delete(UserDeleteRequest userDeleteRequest);
    UserDTO update(Long id, UserUpdateRequest userUpdateRequest);
    UserDTO save (UserRegisterRequest userRegisterRequest);

    Map<String, WeatherDTO> getUsersSavedCitiesWeatherDTO(String username);
    UserDTO addSavedCityToUser(String username, String city);
    UserDTO deleteSavedCityFromUser(String username, String city);
}
