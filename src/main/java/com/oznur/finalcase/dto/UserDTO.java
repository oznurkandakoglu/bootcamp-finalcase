package com.oznur.finalcase.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import java.util.List;

@Getter
@Setter
public class UserDTO{
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private List<String> savedCities;
    //private UserType userType;
}
