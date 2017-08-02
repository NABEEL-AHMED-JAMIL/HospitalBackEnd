package com.ballistic.hospital.config;

import com.ballistic.hospital.security.auth.*;
import com.ballistic.hospital.service.DoctorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM="MY_Ballistic_Test";

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Bean
    public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
        return new TokenAuthenticationFilter();
    }

    @Autowired
    private DoctorDetailsService jwtDoctorDetailsService;

    @Autowired
    private LogoutSuccess logoutSuccess;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService( jwtDoctorDetailsService )
                .passwordEncoder( passwordEncoder() );

    }

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        restAuthenticationEntryPoint
        http.httpBasic().realmName(REALM).and()
            .csrf().ignoringAntMatchers("/auth/login").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
            and().sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
            .exceptionHandling().authenticationEntryPoint( getBasicAuthEntryPoint() ).and()
            .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/docType/addDoctorType").hasAnyRole("DBA","ADMIN").
            antMatchers(HttpMethod.DELETE, "/docType/deleteDoctorType/**").hasAnyRole("DBA","ADMIN").
            antMatchers(HttpMethod.PUT, "/docType/updateDoctorType/**").hasAnyRole("DBA","ADMIN").
            antMatchers("/note/newNote/**").hasAnyRole("DBA","ADMIN").
            antMatchers("/note/deleteNote/**").hasAnyRole("DBA","ADMIN").
            antMatchers("/note/updateNote/**").hasAnyRole("DBA","ADMIN").
            antMatchers("/patient/newPatient").hasAnyRole("DBA","ADMIN").
            antMatchers(HttpMethod.DELETE, "/patient/**").hasAnyRole("DBA","ADMIN").
            antMatchers(HttpMethod.PUT, "/patient/**").hasAnyRole("DBA","ADMIN").
            antMatchers(HttpMethod.POST, "/register").hasAnyRole("DBA").and()
            .formLogin()
            .loginPage("/auth/login")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler).and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
            .logoutSuccessHandler(logoutSuccess)
            .deleteCookies(TOKEN_COOKIE).and()
             .csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
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


    public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

        @Override
        public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
            //Authentication failed, send error response.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

            PrintWriter writer = response.getWriter();
            writer.println("HTTP Status 401 : " + authException.getMessage());
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            setRealmName("MY_Ballistic_Test");
            super.afterPropertiesSet();
        }
    }

}

