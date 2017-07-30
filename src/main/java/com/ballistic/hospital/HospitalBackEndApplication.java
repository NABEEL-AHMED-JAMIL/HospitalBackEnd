package com.ballistic.hospital;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EntityScan(basePackages = "com.ballistic.hospital")
public class HospitalBackEndApplication {



//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Bean
//	CommandLineRunner init(final DoctorRepository doctorRepository) {
//
//		return new CommandLineRunner() {
//
//			@Override
//			public void run(String... arg0) throws Exception {
//
//				doctorRepository.save(
//						new Doctor(4L, "xyz@gmail.com", "xyz", passwordEncoder.encode("ballistic"), "xyz", "xyz",true,true, null, null));
//			}
//
//		};
//
//	}

	public static void main(String[] args) throws Throwable {

		SpringApplication.run(HospitalBackEndApplication.class, args);
	}

}
