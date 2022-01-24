package io.cygert.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @RestController
    @RequestMapping("/test")
    static class HomeController {

        @GetMapping
        String test() {
            return "called /test";
        }

        @GetMapping("/name")
        String name() {
            return "called /test/name";
        }

        @GetMapping("/names")
        String names() {
            return "called /test/names";
        }
    }

    @Configuration
    @EnableWebSecurity
    static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$c89bVLmsFa2/doJr8KPt5e48OU0iIf96h9i8FRxCehS5NmPQobg.W") // admin
                .roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // The most detailed ant matcher must be defined first. Reversing the order of this configuration will make
            // both endpoints insecure.
            http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers("/test/name**").hasRole("ADMIN")
                .mvcMatchers("/test/**").permitAll()
                .and()
                .httpBasic();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

}
