package com.oznur.finalcase.controller.contract.impl;

import com.oznur.finalcase.auth.AuthenticationRequest;
import com.oznur.finalcase.auth.LoginRequest;
import com.oznur.finalcase.config.JwtService;
import com.oznur.finalcase.controller.contract.UserRegistrationControllerContract;
import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.exception.EmailFormatWasEnteredIncorrectly;
import com.oznur.finalcase.exception.UsernameAndPasswordNotMatchException;
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
    @Override
    public UserDTO createUser(AuthenticationRequest authRequestDto) {
        try{
            User user = userRegistrationService.registerAppUser(authRequestDto);
            return UserMapper.INSTANCE.convertToUserDTO(user);
        }
        catch (Exception e){
            throw new EmailFormatWasEnteredIncorrectly("Email format was entered incorrectly!");
        }

    }

    @Override
    public String authenticate(LoginRequest request) {
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            return jwtService.generateToken(user);
        }
        catch (Exception e){
            throw new UsernameAndPasswordNotMatchException("Username and password not match!");
        }

    }
}
