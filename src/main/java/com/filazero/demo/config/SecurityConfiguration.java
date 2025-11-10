package com.filazero.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.filazero.demo.security.JpaUserDetailsService;




@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    @Value("${api-endpoint}")
    String endpoint;

    private final JpaUserDetailsService customerUserDetailsService; 

    public SecurityConfiguration(JpaUserDetailsService customerUserDetailsService) {
        this.customerUserDetailsService = customerUserDetailsService;
    }
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .disable())
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(form -> form.disable())
                .authorizeHttpRequests(auth -> auth
                       
                        .requestMatchers("/h2-console/**").permitAll()
                        
                        .requestMatchers(HttpMethod.POST, endpoint + "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, endpoint + "/auth/login").permitAll() 
                        .requestMatchers(HttpMethod.GET, endpoint + "/products/**").permitAll()
                        
                        .requestMatchers(endpoint + "/profile/**").hasRole("CUSTOMER")
                        
                       
                        .requestMatchers(HttpMethod.POST, endpoint + "/deliveries/**").hasRole("CUSTOMER")
                       
                        .requestMatchers(endpoint + "/detail-deliveries/**").hasRole("CUSTOMER")
                        
                     
                        .requestMatchers(endpoint + "/turns/**").denyAll()
                        .requestMatchers(endpoint + "/notifications/**").denyAll()
                        .requestMatchers(endpoint + "/type-notifications/**").denyAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/products").denyAll()
                        .requestMatchers(HttpMethod.PUT, endpoint + "/products/**").denyAll()
                        .requestMatchers(HttpMethod.DELETE, endpoint + "/products/**").denyAll()

                        // 🔐 Todo lo demás requiere autenticación
                        .anyRequest().authenticated())
                
                //Define el servicio para cargar usuarios
                .userDetailsService(customerUserDetailsService)
                
                // 🔑 Asegura Basic Auth nuevamente
                .httpBasic(withDefaults())
                
                //  Configuración de Logout
                .logout(logout -> logout
                        .logoutUrl(endpoint + "/auth/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                
                //  Gestión de Sesiones (IF_REQUIRED es el valor por defecto para Basic Auth)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}