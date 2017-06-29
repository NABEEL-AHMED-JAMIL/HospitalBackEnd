package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.DoctorType;
import com.ballistic.hospital.repository.DoctorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nabeel on 4/19/2017.
 */
@RestController
@RequestMapping("/docType")
public class DoctorTypeController {
    // this is used
    @Autowired
    private DoctorTypeRepository doctorTypeRepository;

    // post the new DoctorType
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<DoctorType> AdddoctorType(@RequestBody DoctorType doctorType) {

        this.doctorTypeRepository.save(doctorType);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.CREATED);
    }

    // get the All the DoctorType
    @RequestMapping(value="/getAllType", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<List<DoctorType>> getAllTypes() {

        List<DoctorType> doctorTypeList = doctorTypeRepository.findAll();

        if(doctorTypeList.isEmpty()){
            return new ResponseEntity<List<DoctorType>>(doctorTypeList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DoctorType>>(doctorTypeList, HttpStatus.OK);
    }


    //  delete thec Doctor type
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<DoctorType> DeletedoctorType(@PathVariable("id") Long id) {

        this.doctorTypeRepository.delete(id);
        return new ResponseEntity<DoctorType>(HttpStatus.NO_CONTENT);
    }

    // get doctor type by id
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<DoctorType> getdoctorType(@PathVariable("id") Long id) {

        DoctorType doctorType = this.doctorTypeRepository.findOne(id);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.OK);
    }
    // update the DoctorType
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public ResponseEntity<DoctorType> UpdatedoctorType(@RequestBody DoctorType doctorType) {

        this.doctorTypeRepository.save(doctorType);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.OK);
    }

}