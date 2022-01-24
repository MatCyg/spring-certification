package io.cygert.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
        String name(@AuthenticationPrincipal User user, Principal principal) {
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
//        @Autowired
//        void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN");
//        }

        @Bean
        @Override
        public UserDetailsService userDetailsService() {
            UserDetails admin = User.withUsername("admin")
                                    .password(passwordEncoder().encode("admin"))
                                    .roles("ADMIN")
                                    .build();
            return new InMemoryUserDetailsManager(admin);
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
