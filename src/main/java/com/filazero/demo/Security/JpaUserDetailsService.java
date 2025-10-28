package com.filazero.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filazero.demo.customer.CustomerEntity;
import com.filazero.demo.customer.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerEntity customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Customer not found with email: " + email
                ));
        return new SecurityUser(customer);
    }
}

