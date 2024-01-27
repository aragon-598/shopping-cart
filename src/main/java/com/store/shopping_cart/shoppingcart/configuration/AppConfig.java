package com.store.shopping_cart.shoppingcart.configuration;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    //  @Autowired
    // private UserRepository userRepository;

    // UserDetailsService userDetailsService(){
    //     return username -> UserDetailsImp.build(userRepository.findByUsername(username).get());
    // }

    // @Bean
    // BCryptPasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //     return config.getAuthenticationManager();
    // }

    // @Bean
    // AuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    //     // authProvider.setUserDetailsService(userDetailsService());
    //     // authProvider.setPasswordEncoder(passwordEncoder());

    //     return authProvider;
    // }
}