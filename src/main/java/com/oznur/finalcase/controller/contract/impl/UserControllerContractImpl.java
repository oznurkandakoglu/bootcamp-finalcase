package com.oznur.finalcase.controller.contract.impl;

import com.oznur.finalcase.controller.contract.UserControllerContract;
import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserRegisterRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.mapper.UserMapper;
import com.oznur.finalcase.model.WeatherDTO;
import com.oznur.finalcase.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userEntityService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOList(userList);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userEntityService.findById(id).orElseThrow();
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userEntityService.findByUsername(username);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void delete(UserDeleteRequest userDeleteRequest) {
        User user = userEntityService.findByUsername(userDeleteRequest.getUsername());
        /*if(!user.getUsername().equals(userDeleteRequest.username())){
            throw new UsernameAndPhoneNumberNotMatchException(userDeleteRequest.username() + " username and " + userDeleteRequest.phoneNumber() + " phone number not match!");
        }*/
        userEntityService.delete(user);
    }

    @Override
    public UserDTO update(Long id, UserUpdateRequest userUpdateRequest){
        return userEntityService.update(id, userUpdateRequest);
    }

    @Override
    public UserDTO save(UserRegisterRequest userRegisterRequest) {
        User user = UserMapper.INSTANCE.convertToUser(userRegisterRequest);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public Map<String, WeatherDTO> getUsersSavedCitiesWeatherDTO(String username) {
        return userEntityService.getUsersSavedCitiesWeatherDTO(username);
    }

    @Override
    public UserDTO addSavedCityToUser(String username, String city) {
        User user = userEntityService.addSavedCityToUser(username,city);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO deleteSavedCityFromUser(String username, String city) {
        User user = userEntityService.deleteSavedCityFromUser(username,city);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

}
