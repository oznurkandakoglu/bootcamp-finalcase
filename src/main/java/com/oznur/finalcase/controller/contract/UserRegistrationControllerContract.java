package com.oznur.finalcase.controller.contract;

import com.oznur.finalcase.auth.AuthenticationRequest;
import com.oznur.finalcase.auth.LoginRequest;
import com.oznur.finalcase.dto.UserDTO;

public interface UserRegistrationControllerContract {
    UserDTO createUser(AuthenticationRequest authRequestDto);
    String authenticate(LoginRequest request);
}
