package ar.com.eb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration//clase de cong spring
@EnableWebSecurity
public class SecurityConfig {
    

        @Autowired
        private UserDetailsService userDetailsService;
        
        
        public BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
            build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.authorizeHttpRequests()
                    .requestMatchers("/editar/**", "agregar/**", "/eliminar").hasRole("ADMIN")
                    .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .and()
                    .exceptionHandling().accessDeniedPage("/errores/403");
                   

            //.loginPage("/login"); Esto es para usar una pagina de login personal.
            return httpSecurity.build();
        }
    }
