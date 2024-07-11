package com.rmeunier.catworld.config;

import com.rmeunier.catworld.user.repo.UserAccountRepository;
import com.rmeunier.catworld.user.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserAccountRepository userAccountRepository;

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfiguration(UserAccountRepository userAccountRepository, CustomUserDetailsService customUserDetailsService) {
        this.userAccountRepository = userAccountRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
//        return username -> userAccountRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Username not found: " + username));
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/user/signup**", "/error**", "/js/**", "/css/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(lOut -> lOut
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutUrl("/api/user/logout")
                        .logoutSuccessUrl("/api/user/logout?success")
                        .permitAll()
                )
                .httpBasic(httpBasicConfigurer -> httpBasicConfigurer.authenticationEntryPoint(authenticationEntryPoint()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Realm");
        return entryPoint;
    }
}
