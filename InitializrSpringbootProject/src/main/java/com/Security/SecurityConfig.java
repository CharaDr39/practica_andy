package com.Security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.ServiceImplementation.UserDetailsServiceImpl; 


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserDetailsServiceImpl userDetailsServiceImpl;


    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Paso 7.1.2.7.1: Configurar la autorización de URLs
        http.authorizeHttpRequests(auth -> {
                auth

                    .requestMatchers("/admin/**").hasRole("ADMIN")

                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")

                    .anyRequest().permitAll();
            })

            .formLogin(form -> form

                .loginPage("/login")

                .permitAll()

                .defaultSuccessUrl("/", true)
            )

            .logout(logout -> logout.permitAll());
        

        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
}
