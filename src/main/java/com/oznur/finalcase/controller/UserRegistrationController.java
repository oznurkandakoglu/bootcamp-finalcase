package com.oznur.finalcase.controller;

import com.oznur.finalcase.auth.AuthenticationRequest;
import com.oznur.finalcase.auth.LoginRequest;
import com.oznur.finalcase.config.JwtService;
import com.oznur.finalcase.dto.UserDTO;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.general.RestResponse;
import com.oznur.finalcase.mapper.UserMapper;
import com.oznur.finalcase.repository.UserRepository;
import com.oznur.finalcase.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserRegistrationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("/app-user")
    public ResponseEntity<RestResponse<UserDTO>> createAppUser(@RequestBody AuthenticationRequest authRequestDto) {
        User user = userRegistrationService.registerAppUser(authRequestDto);
        UserDTO userDTO = UserMapper.INSTANCE.convertToUserDTO(user);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<RestResponse<String>> authenticate(@RequestBody LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(RestResponse.of(jwtToken));
    }
}
