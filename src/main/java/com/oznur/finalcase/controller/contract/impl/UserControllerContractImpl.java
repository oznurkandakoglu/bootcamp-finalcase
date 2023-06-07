package com.oznur.finalcase.controller.contract.impl;

import com.oznur.finalcase.controller.contract.UserControllerContract;
import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserRegisterRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.exception.IdNotFoundException;
import com.oznur.finalcase.exception.SavedCitiesNotFoundException;
import com.oznur.finalcase.exception.UserNotFoundException;
import com.oznur.finalcase.kafka.producer.KafkaProducerService;
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
    private final KafkaProducerService kafkaProducerService;
    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userEntityService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOList(userList);
    }

    @Override
    public UserDTO findById(Long id) {
        try{
            kafkaProducerService.sendMessage("Find by id method called!", "logInfo");
            User user = userEntityService.findById(id).orElseThrow();
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            throw new IdNotFoundException("Id not found!");
        }


    }

    @Override
    public UserDTO findByUsername(String username) {
        try {
            User user = userEntityService.findByUsername(username);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public void delete(UserDeleteRequest userDeleteRequest) {
        try{
            User user = userEntityService.findByUsername(userDeleteRequest.getUsername());
            userEntityService.delete(user);
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public UserDTO update(Long id, UserUpdateRequest userUpdateRequest){
        try{
            return userEntityService.update(id, userUpdateRequest);
        }
        catch (Exception e){
            throw new IdNotFoundException("Id not found!");
        }

    }

    @Override
    public UserDTO save(UserRegisterRequest userRegisterRequest) {
        User user = UserMapper.INSTANCE.convertToUser(userRegisterRequest);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public Map<String, WeatherDTO> getUsersSavedCitiesWeatherDTO(String username) {
        try{
            return userEntityService.getUsersSavedCitiesWeatherDTO(username);
        }
        catch(Exception e){
            throw new SavedCitiesNotFoundException("This user has no saved cities.");
        }

    }

    @Override
    public UserDTO addSavedCityToUser(String username, String city) {
        try{
            User user = userEntityService.addSavedCityToUser(username,city);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public UserDTO deleteSavedCityFromUser(String username, String city) {
        try{
            User user = userEntityService.deleteSavedCityFromUser(username,city);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            throw new UserNotFoundException("City not found!");
        }

    }

}
