package com.oznur.finalcase.controller.contract.impl;

import com.oznur.finalcase.controller.contract.UserControllerContract;
import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.dto.UserDeleteRequest;
import com.oznur.finalcase.dto.UserRegisterRequest;
import com.oznur.finalcase.dto.UserUpdateRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.exception.CityNotFoundException;
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
            kafkaProducerService.sendMessage("Find by id method called!", "infoLogs");
            User user = userEntityService.findById(id).orElseThrow();
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("Id not found!", "errorLogs");
            throw new IdNotFoundException("Id not found!");
        }


    }

    @Override
    public UserDTO findByUsername(String username) {
        try {
            kafkaProducerService.sendMessage("Find by username method called!", "infoLogs");
            User user = userEntityService.findByUsername(username);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("User not found!", "errorLogs");
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public void delete(UserDeleteRequest userDeleteRequest) {
        try{
            kafkaProducerService.sendMessage("Delete method called!", "infoLogs");
            User user = userEntityService.findByUsername(userDeleteRequest.getUsername());
            userEntityService.delete(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("User not found!", "errorLogs");
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public UserDTO update(Long id, UserUpdateRequest userUpdateRequest){
        try{
            kafkaProducerService.sendMessage("Update method called!", "infoLogs");
            return userEntityService.update(id, userUpdateRequest);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("Id not found!", "errorLogs");
            throw new IdNotFoundException("Id not found!");
        }

    }

    @Override
    public UserDTO save(UserRegisterRequest userRegisterRequest) {
        kafkaProducerService.sendMessage("Save method called!", "infoLogs");
        User user = UserMapper.INSTANCE.convertToUser(userRegisterRequest);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public Map<String, WeatherDTO> getUsersSavedCitiesWeatherDTO(String username) {
        try{
            kafkaProducerService.sendMessage("Get user saved cities method called!", "infoLogs");
            return userEntityService.getUsersSavedCitiesWeatherDTO(username);
        }
        catch(Exception e){
            kafkaProducerService.sendMessage("User not found!", "errorLogs");
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public UserDTO addSavedCityToUser(String username, String city) {
        try{
            kafkaProducerService.sendMessage("Add saved city to user method called!", "infoLogs");
            User user = userEntityService.addSavedCityToUser(username,city);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("User not found!", "errorLogs");
            throw new UserNotFoundException("User not found!");
        }

    }

    @Override
    public UserDTO deleteSavedCityFromUser(String username, String city) {
        try{
            kafkaProducerService.sendMessage("Delete saved city from user method called!", "infoLogs");
            User user = userEntityService.deleteSavedCityFromUser(username,city);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("City not found!", "errorLogs");
            throw new CityNotFoundException("City not found!");
        }

    }

}
