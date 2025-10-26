package com.filazero.demo.security;

import org.springframework.stereotype.Service;
 
import com.filazero.demo.customer.CustomerRepository;

@Service
public class JpaUserDetailsService implements CustomerDetailsService {

        private CustomerRepository userRepository;

    public JpaUserDetailsService(CustomerRepository userRepository) {
        this.customerCustomerRepository = userRepository;
    }

        @Override
    public CustomerDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        return userRepository.findByEmail(email)
                .map(SecurityCustomer::new)
                .orElseThrow(() -> new UserNotFoundException("User not found with this email"));

    }
}