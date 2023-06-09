package com.oznur.finalcase.service;

import com.oznur.finalcase.dto.AuthenticationRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.enums.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserEntityService userEntityService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRegistrationService(UserEntityService userEntityService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userEntityService = userEntityService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerAppUser(AuthenticationRequest authRequestDto){
        User user = createValidUser(authRequestDto);
        user.setRole(Role.ROLE_USER);
        return this.userEntityService.save(user);
    }

    public User registerAdminUser(AuthenticationRequest authRequestDto){
        User user = createValidUser(authRequestDto);
        user.setRole(Role.ROLE_ADMIN);
        return this.userEntityService.save(user);
    }

    public User createValidUser(AuthenticationRequest authRequestDto) {
        User user = new User();
        user.setUsername(authRequestDto.getUsername());
        user.setEmail(authRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(authRequestDto.getPassword()));
        return user;
    }
}
