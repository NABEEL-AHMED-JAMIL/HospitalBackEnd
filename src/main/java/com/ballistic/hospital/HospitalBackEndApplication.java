package com.ballistic.hospital;

import com.ballistic.hospital.dto.DoctorDetailDTO;
import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
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

	@Autowired
	private DoctorRepository doctorRepository;

	public static void main(String[] args) {

			SpringApplication.run(HospitalBackEndApplication.class, args);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Doctor doctor = doctorRepository.findByUserName(userName);
		if(doctor != null) {
			return new DoctorDetailDTO(doctor);
		}else {
			throw new UsernameNotFoundException("Could not find the user"+ userName);
		}

	}

}
