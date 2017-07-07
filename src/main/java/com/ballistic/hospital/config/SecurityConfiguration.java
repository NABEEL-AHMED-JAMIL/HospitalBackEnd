package com.ballistic.hospital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Created by Lycus 01 on 7/3/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().cors().and()
                .authorizeRequests().antMatchers("/test/getPakistan").permitAll()
                .antMatchers("/note/**").hasAnyAuthority("ADMIN","DBA","USER").anyRequest().fullyAuthenticated()
                .antMatchers("/docType/**").hasAnyAuthority("ADMIN","DBA","USER").anyRequest().fullyAuthenticated()
                .antMatchers("/patient/**").hasAnyAuthority("ADMIN","DBA","USER").anyRequest().fullyAuthenticated()
                .antMatchers("/doctor/**").hasAnyAuthority("ADMIN","DBA","USER").anyRequest().fullyAuthenticated()
                .antMatchers("/register").hasAnyAuthority("ADMIN","DBA","USER").anyRequest().fullyAuthenticated().and()
                .addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login").usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/patient/**")
                .failureUrl("/login?err=1")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/loginPage?logout")
                .permitAll().
                and().httpBasic();


    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }


}
