package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.dto.DoctorDTO;
import com.ballistic.hospital.repository.DoctorRepository;
import com.ballistic.hospital.service.EmailService;
import com.ballistic.hospital.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lycus 01 on 7/4/2017.
 */
@RestController
@RequestMapping("/auth")
public class LogInController {

    @Autowired
    private Util util;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EmailService emailService;

    //ok test call
    @RequestMapping(value="/login",  method = RequestMethod.POST)
    public ResponseEntity<Doctor> login(@RequestBody DoctorDTO doctor ) {

        Doctor doctor1 = doctorRepository.findByUserName(doctor.getUserName());
        if(doctor1 != null){
            // check the user true
            if(doctor.getUserName().equals(doctor1.getUserName())){
                if(doctor.getPassWord().equals(doctor1.getPassWord())){
                    // set the null for password
                    doctor1.setPassWord(null);
                    return new ResponseEntity<Doctor>(doctor1, HttpStatus.OK);
                }else {
                    return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
                }

            }else {
                return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
            }
        }else{
            System.out.println("Doctor null");
            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);

        }
    }

    //ok test call
    @RequestMapping(value="/forgotPassword",  method = RequestMethod.POST)
    public ResponseEntity<String> forgotPassword(@RequestBody String email ) {

        try {
            emailService.sendSimpleMessage(email ,"Login PassWord", null);
            Doctor doctor = doctorRepository.findByEmail(email);
            if (doctor.equals(null)) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            } else {
                // check your email and click back button
                emailService.sendSimpleMessage(email ,"Login PassWord", doctor);
                return new ResponseEntity<String>(email, HttpStatus.OK);
            }

        } catch (NullPointerException e1) {
            System.out.println("Error:--> InValid Email");
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }


}
