package com.ballistic.hospital;

import com.ballistic.hospital.services.DocterDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
//@EnableWebSecurity
@ComponentScan(basePackages = "com.ballistic.hospital")

public class HospitalBackEndApplication { // extends WebSecurityConfigurerAdapter {


//	@Autowired
//	private DocterDetailsService docterDetailsService;


	public static void main(String[] args) {
		SpringApplication.run(HospitalBackEndApplication.class, args);
	}


//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//
//
////		http.httpBasic().and().authorizeRequests().//
////				antMatchers(HttpMethod.POST, "/patient/addPatient").hasRole("user").//
////				antMatchers(HttpMethod.GET, "/patient/getAllPatient").hasRole("user").//
////				antMatchers(HttpMethod.GET, "/patient/notes/**").hasRole("user").
////				antMatchers(HttpMethod.DELETE, "/patient/**").hasRole("user").
////				antMatchers(HttpMethod.PUT, "/patient/**").hasRole("user").and().csrf().disable();
//
//
//		http.authorizeRequests()
//				.antMatchers(HttpMethod.POST,"/patient/addPatient").hasRole("user").
//				 and()
//				.formLogin().loginPage("/docter/login")
//				.defaultSuccessUrl("/patient/getAllPatient")
//				.failureUrl("/docter/login?error")
//				.usernameParameter("username").passwordParameter("password")
//				.and()
//				.logout().logoutSuccessUrl("/docter/login?logout").permitAll();
//
//
//		}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(docterDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	}


}
