package com.example.somnium_vacations.configuration;

import com.example.somnium_vacations.services.users.CustomUserDetails;
import com.example.somnium_vacations.services.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/img/**", "/css/**", "/fonts/**").permitAll()
                        .requestMatchers("/", "/log", "loged", "/registration", "/registration/**", "/vacancy/{id}/sendResponse").permitAll()
                        .requestMatchers("/adminPages/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .oauth2Login(oauth2Customize ->
                        oauth2Customize
                                .loginPage("/oauth2/authorization/google")
                                .defaultSuccessUrl("/loged", true)
                                .failureUrl("/log?error=true"))
                .formLogin((login) -> login
                        .loginPage("/log").permitAll()
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/searchVacancies", true)
                        .failureUrl("/log?error=true")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.accessDeniedPage("/accessDenied"));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
