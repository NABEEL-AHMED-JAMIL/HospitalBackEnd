package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lycus 01 on 7/4/2017.
 */
@RestController
public class RegisterController {


    // login user
    @Autowired
    private DoctorRepository doctorRepository;

    // post the new doctor
    @RequestMapping(value="/register",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> registerUser(@RequestBody Doctor doctor ) {

        Doctor doctor1 = doctorRepository.findByUserName(doctor.getUserName());
        if(doctor1 != null){
            // already exist
            return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);

        }else{

            // list of role come from the front-end
            this.doctorRepository.save(doctor);
            return new ResponseEntity<Doctor>(doctor1, HttpStatus.OK);
        }


    }
}
