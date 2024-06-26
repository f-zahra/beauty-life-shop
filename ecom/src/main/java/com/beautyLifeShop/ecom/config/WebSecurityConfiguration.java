package com.beautyLifeShop.ecom.config;


import com.beautyLifeShop.ecom.models.AuthenticationSuccessHandler;
import com.beautyLifeShop.ecom.repository.UserRepository;
import com.beautyLifeShop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//allow role based authorization
public class WebSecurityConfiguration {


    @Autowired
    private UserService userService;

    @Autowired
    private Encoder passwordEncoder;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private TokenService tokenService;
    //customize the filter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable()
                .addFilter(new TokenAuthenticationFilter(this.authenticationManager(authenticationConfiguration), tokenService)); // Add custom filter;
        httpSecurity.authorizeHttpRequests(registry ->
                registry.requestMatchers("/api/products/**", "/images/**","/api/login","api/home","api/cart/**", "api/user/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated()

        );
        httpSecurity.formLogin().disable()/*.defaultSuccessUrl("/api/user/user-dashboard", true)*/;
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Session creation policy


        return httpSecurity.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //get user data for authentication
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }




}
