package com.oznur.finalcase.entity;

import com.oznur.finalcase.enums.Role;
import com.oznur.finalcase.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "User", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    private Long id;

    @NotBlank
    @Column(name = "USERNAME", length = 100 , nullable = false)
    private String username;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PHONE_NUMBER", length = 100, nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "PASSWORD", length = 100,nullable = false)
    private String password;

    private List<String> savedCities;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 20)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername(){return username;}
    @Override
    public String getPassword(){return password;}
}


