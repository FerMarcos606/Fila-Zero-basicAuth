package com.filazero.demo.security;

import com.filazero.demo.customer.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final CustomerEntity customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna un único rol como autoridad
        return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")); // siempre CUSTOMER
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail(); // o el campo que uses para login
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

