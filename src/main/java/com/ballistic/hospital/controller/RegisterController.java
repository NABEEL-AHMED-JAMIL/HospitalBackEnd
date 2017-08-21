package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.alps.Doc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static com.ballistic.hospital.dto.ActionConsts.*;


/**
 * Created by Lycus 01 on 7/4/2017.
 */
@RestController
public class RegisterController {


    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private Doctor doctor;


    @RequestMapping(value = REGISTER,  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DBA')")
    public ResponseEntity<Doctor> registerUser(@RequestBody Doctor doctor ) {

        if(doctor.getPassword().equals(doctor.getConfirmPassword())){
            // know used the query generator to find the email if exist then error show
            if(doctorRepository.findByUsernameAndEmail(doctor.getUsername(), doctor.getEmail()) != null){
                return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
            }else{
                this.doctor = new Doctor();
                this.doctor.setEmail(doctor.getEmail());
                this.doctor.setUsername(doctor.getUsername());
                this.doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
                this.doctor.setFirstname(doctor.getFirstname());
                this.doctor.setLastname(doctor.getLastname());
                this.doctor.setGender(doctor.isGender());
                this.doctor.setActive(doctor.isActive());
                this.doctor.setRoles(doctor.getRoles());
                this.doctor.setDoctorType(doctor.getDoctorType());
                this.doctorRepository.save(this.doctor);
                return new ResponseEntity<Doctor>(this.doctor, HttpStatus.OK);
            }

        }else{
            return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
        }
    }
}





