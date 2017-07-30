package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;


    @RequestMapping(value = "/getAllDoctor" ,method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> getAllDoctor() {

        List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty()){
            return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDoctor",method = RequestMethod.GET)
    public ResponseEntity<Doctor> getDoctor(@RequestBody Doctor doctor) {

        Doctor doctor1 = doctorRepository.findByUsername(doctor.getUsername());
        //System.out.print(doctor1.toString());
        if(doctor1 == null ){
            //
            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);

        }
        else{
            return new ResponseEntity<Doctor>(doctor1,HttpStatus.OK);
        }

    }
}
