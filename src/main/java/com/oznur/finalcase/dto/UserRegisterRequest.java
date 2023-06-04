package com.oznur.finalcase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest{
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
