package com.oznur.finalcase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationServiceTest {

    @Mock
    private UserEntityService userEntityService;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void registerAppUser() {
    }

    @Test
    void registerAdminUser() {
    }
}