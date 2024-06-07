package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private UUID id;

    private String name;

    private String password;

    private String email;

    private String userName;

    public UserDetailsImpl(UUID id, String userName, String email, String name, Collection<? extends GrantedAuthority> authorities) {
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getName(),
                new ArrayList<>());
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
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
}
