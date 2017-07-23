package com.ballistic.hospital;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EntityScan(basePackages = "com.ballistic.hospital")
public class HospitalBackEndApplication {


	public static void main(String[] args) throws Throwable {

		SpringApplication.run(HospitalBackEndApplication.class, args);
	}

}
