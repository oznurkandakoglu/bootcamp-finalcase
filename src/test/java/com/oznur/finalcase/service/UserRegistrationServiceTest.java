package com.oznur.finalcase.service;

import com.oznur.finalcase.auth.AuthenticationRequest;
import com.oznur.finalcase.entity.User;
import com.oznur.finalcase.enums.Role;
import com.oznur.finalcase.service.UserEntityService;
import com.oznur.finalcase.service.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationServiceTest {

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void registerAppUser_ShouldCreateValidUserWithUserRole() {

        AuthenticationRequest authRequestDto = new AuthenticationRequest();
        authRequestDto.setUsername("username");
        authRequestDto.setEmail("user@gmail.com");
        authRequestDto.setPassword("password");

        User expectedUser = new User();
        expectedUser.setUsername("username");
        expectedUser.setEmail("user@gmail.com");
        expectedUser.setPassword("encoded_password");
        expectedUser.setRole(Role.ROLE_USER);

        when(bCryptPasswordEncoder.encode(authRequestDto.getPassword())).thenReturn("encoded_password");
        when(userEntityService.save(any(User.class))).thenReturn(expectedUser);


        User result = userRegistrationService.registerAppUser(authRequestDto);


        verify(userEntityService, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(expectedUser, result);
        assertEquals(expectedUser.getUsername(), capturedUser.getUsername());
        assertEquals(expectedUser.getEmail(), capturedUser.getEmail());
        assertEquals(expectedUser.getPassword(), capturedUser.getPassword());
        assertEquals(expectedUser.getRole(), capturedUser.getRole());
    }

    @Test
    void registerAdminUser_ShouldCreateValidUserWithAdminRole() {

        AuthenticationRequest authRequestDto = new AuthenticationRequest();
        authRequestDto.setUsername("admin");
        authRequestDto.setEmail("admin@example.com");
        authRequestDto.setPassword("password");

        User expectedUser = new User();
        expectedUser.setUsername("admin");
        expectedUser.setEmail("admin@example.com");
        expectedUser.setPassword("encoded_password");
        expectedUser.setRole(Role.ROLE_ADMIN);

        when(bCryptPasswordEncoder.encode(authRequestDto.getPassword())).thenReturn("encoded_password");
        when(userEntityService.save(any(User.class))).thenReturn(expectedUser);

        User result = userRegistrationService.registerAdminUser(authRequestDto);

        verify(userEntityService, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(expectedUser, result);
        assertEquals(expectedUser.getUsername(), capturedUser.getUsername());
        assertEquals(expectedUser.getEmail(), capturedUser.getEmail());
        assertEquals(expectedUser.getPassword(), capturedUser.getPassword());
        assertEquals(expectedUser.getRole(), capturedUser.getRole());
    }

    @Test
    public void testCreateValidUser() {
        String login = "testuser";
        String password = "testpassword";
        AuthenticationRequest authRequestDto = new AuthenticationRequest();
        authRequestDto.setUsername(login);
        authRequestDto.setPassword(password);
        User validUser = userRegistrationService.createValidUser(authRequestDto);
        validUser.setRole(Role.ROLE_USER);
        validUser.setPassword(password);
        Mockito.when(userEntityService.save(Mockito.any())).thenReturn(validUser);
        Mockito.when(bCryptPasswordEncoder.encode(Mockito.any())).thenReturn(password);
        User user = userRegistrationService.registerAppUser(authRequestDto);

        assertEquals(login, user.getUsername());
        assertEquals("testpassword", user.getPassword());
    }
}
