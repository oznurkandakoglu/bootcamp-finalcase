package com.oznur.finalcase.controller.contract.impl;

import com.oznur.finalcase.dto.AuthenticationRequest;
import com.oznur.finalcase.dto.LoginRequest;
import com.oznur.finalcase.config.JwtService;
import com.oznur.finalcase.controller.contract.UserRegistrationControllerContract;
import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.exception.EmailFormatWasEnteredIncorrectly;
import com.oznur.finalcase.exception.UsernameAndPasswordNotMatchException;
import com.oznur.finalcase.kafka.producer.KafkaProducerService;
import com.oznur.finalcase.mapper.UserMapper;
import com.oznur.finalcase.repository.UserRepository;
import com.oznur.finalcase.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRegistrationControllerContractImpl implements UserRegistrationControllerContract {

    private final UserRegistrationService userRegistrationService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final KafkaProducerService kafkaProducerService;
    @Override
    public UserDTO createUser(AuthenticationRequest authRequestDto) {
        try{
            kafkaProducerService.sendMessage("Create user method called!", "infoLogs");
            User user = userRegistrationService.registerAppUser(authRequestDto);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("Email format was entered incorrectly!", "errorLogs");
            throw new EmailFormatWasEnteredIncorrectly("Email format was entered incorrectly!");
        }

    }

    @Override
    public String authenticate(LoginRequest request) {
        try{
            kafkaProducerService.sendMessage("Authenticate method called!", "infoLogs");
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            return jwtService.generateToken(user);
        }
        catch (Exception e){
            kafkaProducerService.sendMessage("Username and password not match!", "errorLogs");
            throw new UsernameAndPasswordNotMatchException("Username and password not match!");
        }

    }
}
