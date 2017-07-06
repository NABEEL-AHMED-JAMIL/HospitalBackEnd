package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.alps.Doc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Lycus 01 on 7/4/2017.
 */
@RestController
public class LogInController {

    @Autowired
    private DoctorRepository doctorRepository;

    @RequestMapping(value="/login")
    public ResponseEntity<Principal> logInUser(Principal principal) {
        return new ResponseEntity<Principal>(principal,HttpStatus.OK);
    }

}
