package com.ballistic.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ballistic.hospital")

public class HospitalBackEndApplication{

	public static void main(String[] args) {

			SpringApplication.run(HospitalBackEndApplication.class, args);
	}


}
