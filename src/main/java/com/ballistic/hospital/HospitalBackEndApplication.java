package com.ballistic.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.ballistic.hospital")
public class HospitalBackEndApplication {


	public static void main(String[] args) throws Throwable {

			SpringApplication.run(HospitalBackEndApplication.class, args);
	}

}
