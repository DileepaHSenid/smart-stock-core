package com.hsenid.smartstock.exception;

import com.hsenid.smartstock.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring security filter chain");

        // Configure HTTP security
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults()) // Enable CORS with default settings
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/home", "/signin", "/signup","/orders/**","/stocks/**","/users/**","/suppliers/**", "/products/**","/categories/**").permitAll()
//                        .requestMatchers("/orders").hasAuthority("ADMIN")
//                        .requestMatchers("").hasAuthority("MAINTAINER")
//                        .requestMatchers("/CreateSupplier").hasAuthority(UserRole.ADMIN.name())
//                        .requestMatchers("/products/create").hasAuthority(UserRole.ADMIN.name())
//                        .requestMatchers("/CreateProduct").hasAuthority(UserRole.ADMIN.name())
//                        .requestMatchers(HttpMethod.POST, "/supplier").hasRole("ADMIN")

                        .anyRequest().authenticated())
                        .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        log.info("Security filter chain configured successfully");
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder to encode passwords
        return new BCryptPasswordEncoder();
    }

    // Define the authentication manager bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Get the authentication manager from the authentication configuration
        return authenticationConfiguration.getAuthenticationManager();
    }
    // CORS configuration
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("http://localhost:4200"); // Allow your frontend's origin
        corsConfiguration.addAllowedHeader("*"); // Allow all headers
        corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}