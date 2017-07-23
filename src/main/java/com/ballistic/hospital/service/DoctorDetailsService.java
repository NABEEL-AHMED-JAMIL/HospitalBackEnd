package com.ballistic.hospital.service;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Lycus 01 on 7/23/2017.
 */
// this one find the user and return the result
@Service
public class DoctorDetailsService implements UserDetailsService{

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private Util util;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.util.showLine();
        System.out.println("Start finding user process......");
        Doctor doctor = doctorRepository.findByUsername(username);
        System.out.println("RESULT finding of user process......\n"+ doctor.toString());
        this.util.showLine();

        if(doctor == null){
            this.util.showLine();
            System.out.println("OOOOP finding user process......Error");
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else {
            System.out.println("Returning result of finding user process......success");
            return  doctor;
        }
    }
}
