package com.oznur.finalcase.controller;

import com.oznur.finalcase.controller.contract.UserControllerContract;
import com.oznur.finalcase.dto.*;
import com.oznur.finalcase.general.RestResponse;
import com.oznur.finalcase.model.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserControllerContract userControllerContract;


    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        List<UserDTO> userDTOList = userControllerContract.findAll();
        return  ResponseEntity.ok(RestResponse.of(userDTOList));
    }


    @DeleteMapping
    public ResponseEntity<RestResponse<String>> delete(@RequestBody UserDeleteRequest userDeleteRequest){
        userControllerContract.delete(userDeleteRequest);
        return ResponseEntity.ok(RestResponse.of("Deleted!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable Long id){
        UserDTO userDTO = userControllerContract.findById(id);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<RestResponse<UserDTO>> findByUsername(@PathVariable String username){
        UserDTO userDTO = userControllerContract.findByUsername(username);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> update(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest){
        UserDTO userDTO = userControllerContract.update(id, userUpdateRequest);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @GetMapping("/savedcities/{username}")
    public ResponseEntity<RestResponse<Map<String,WeatherDTO>>> getSavedCitiesWeatherDataOfUser(@PathVariable String username){
        Map<String, WeatherDTO> userMap = userControllerContract.getUsersSavedCitiesWeatherDTO(username);
        return ResponseEntity.ok(RestResponse.of(userMap));
    }

    @PostMapping("/savedcities/save/{username}")
    public ResponseEntity<RestResponse<UserDTO>> addSavedCityToUser(@PathVariable String username, @RequestBody UserSaveCityRequest userSaveCityRequest){
        UserDTO userDTO = userControllerContract.addSavedCityToUser(username, userSaveCityRequest.getCity());
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/savedcities/delete/{username}")
    public ResponseEntity<RestResponse<UserDTO>> deleteSavedCityFromUser(@PathVariable String username, @RequestBody UserDeleteCityRequest userDeleteCityRequest){
        UserDTO userDTO = userControllerContract.deleteSavedCityFromUser(username, userDeleteCityRequest.getCity());
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }
}
