package com.filazero.demo.security;

import com.filazero.demo.customer.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final CustomerEntity customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // o devolver roles si tenés
    }

    @Override
    public String getPassword() {
        return customer.getPassword(); // asegurate que exista
    }

    @Override
    public String getUsername() {
        return customer.getEmail(); // o el campo que uses como identificador
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
