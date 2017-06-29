package com.ballistic.hospital;

import com.ballistic.hospital.dto.DoctorDetailDTO;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ballistic.hospital")
public class HospitalBackEndApplication implements UserDetailsService {

	public static void main(String[] args) {

			SpringApplication.run(HospitalBackEndApplication.class, args);
	}


	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new DoctorDetailDTO(doctorRepository.findByUserName(email));
	}
}
