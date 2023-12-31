package ar.com.eb.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration//clase de cong spring
@EnableWebSecurity
public class SecurityConfig {
    

        @Bean
        public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
            UserDetails user = User.withUsername("user")
                    .password(passwordEncoder.encode("user"))
                    .roles("USER")
                    .build();

            UserDetails admin = User.withUsername("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles("USER", "ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.authorizeHttpRequests()
                    .requestMatchers("/editar/**", "agregar/**", "/eliminar").hasRole("ADMIN")
                    .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .exceptionHandling().accessDeniedPage("/errores/403");
                   

            //.loginPage("/login"); Esto es para usar una pagina de login personal.
            return httpSecurity.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

    }
