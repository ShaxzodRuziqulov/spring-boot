package com.example.demo.config;

import com.example.demo.Domain.security.JWTTokenProvider;
import com.example.demo.Domain.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;

    private final JWTTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> authentication; // xarolik  bor

    }

    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService, JWTTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.authorizeRequests()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/api/employees").hasRole("ADMIN")
                .requestMatchers("/api/employees/*").hasAnyRole("ADMIN,USER")
                .requestMatchers("/api/students/all").permitAll()
                .anyRequest().authenticated() // Barcha so'rovlarni autentifikatsiyadan o'tkazadi
                .and();
        http.with(new JwtConfigurer(jwtTokenProvider), customizer -> {
                    // Qo'shimcha customizatsiya uchun . Chat GPTdan olindi.
                })
                .httpBasic(withDefaults()); // HTTP Basic Authentication sozlamalari

        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Autowired // Autowired ishlatilar ekan @Bean ni o'rniga
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
////                .inMemoryAuthentication().withUser("admin")
////                .password(passwordEncoder().encode("admin123")).roles("ADMIN");
//                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
}