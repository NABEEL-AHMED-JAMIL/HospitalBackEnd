package com.ballistic.hospital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Ballistic 01 on 6/18/2017.
 */
// --->ok----> final
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/note/**").hasAnyRole("USER", "ADMIN", "DBA").anyRequest().fullyAuthenticated()
                .antMatchers("/docType/**").hasAnyRole("USER", "ADMIN", "DBA").anyRequest().fullyAuthenticated()
                .antMatchers("/patient/**").hasAnyRole("USER", "ADMIN", "DBA").anyRequest().fullyAuthenticated()
                .antMatchers("/doctor/**").hasAnyRole("USER", "ADMIN", "DBA").anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home")
                .failureUrl("/login?error").usernameParameter("username").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();
    }

}
