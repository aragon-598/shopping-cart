package com.store.shopping_cart.shoppingcart.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    // @Autowired
    // private JwtProvider jwtProvider;

    @Autowired
    JwtEntryPoint entryPoint;
    
    public final String[] PUBLIC_URL = {"/auth/login","/auth/register"};

    // public final String[] ADMIN_URL_ACCESS = {"",""};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            // .authorizeHttpRequests(req -> {
            //     req.requestMatchers("/home/index").hasAuthority(RoleName.ADMIN_ROLE.name());
            // })
            .authorizeHttpRequests(requests -> {
                requests.requestMatchers(PUBLIC_URL).permitAll().anyRequest().authenticated();
            })
            .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint))
            .sessionManagement(management -> {
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            // .authenticationProvider(jwtProvider)
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
