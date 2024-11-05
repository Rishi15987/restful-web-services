package com.rest.webservices.restful_web_services.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
    public class springSecurityConfiguration {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //All requests should be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        //If a request is not authenticated, a webpage is shown
        http.httpBasic(Customizer.withDefaults());

        //CSRF -. POST , PUT
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(csrf -> csrf.disable()) // Disable CSRF if necessary
//            .authorizeRequests(authorizeRequests ->
//                    authorizeRequests
//                            .anyMatchers("/public/**", "/login").permitAll() // Public endpoints
//                            .anyRequest().authenticated()
//            )
//            .formLogin(formLogin ->
//                    formLogin
//                            .loginPage("/login")
//                            .permitAll()
//            );
//
//    return http.build();
//}
}
