package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nabeel on 4/15/2017.
 */
//hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')
//hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')
//hasAnyRole("USER", "ADMIN", "DBA")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    // repository for user...
    @Autowired
    private DoctorRepository doctorRepository;

    // post the new doctor
    @RequestMapping(value="/register",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<Doctor> registerUser(@RequestBody Doctor doctor ) {

        Doctor doctor1 = doctorRepository.findByUserName(doctor.getUserName());

        if(doctor1 != null){
            //
            return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);

        }else{
            doctor.setRole("USER");
            // encode the passWord and decode the passWord
            this.doctorRepository.save(doctor);
            return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
        }


    }

    //-------------------Retrieve All Doctor's----------------
    @RequestMapping(value = "/doctors" ,method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<List<Doctor>> listAlldoctor() {

       List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty()){

            return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
    }

      @RequestMapping(value="/login",  method = RequestMethod.POST)
      @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
      public ResponseEntity<Doctor> logInUser(@RequestBody Doctor doctor) {

          Doctor doctor1 = doctorRepository.findByUserName(doctor.getUserName());
          //System.out.print(doctor1.toString());
          if(doctor1 == null ){
              //
              return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);

          }else{

              if(doctor.getUserName().equals(doctor1.getUserName()) && doctor.getPassword().equals(doctor1.getPassword())){

                  return new ResponseEntity<Doctor>(doctor1,HttpStatus.OK);

              }else {

                  return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
              }
          }



    }




}
