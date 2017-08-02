package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.DoctorType;
import com.ballistic.hospital.repository.DoctorTypeRepository;
import com.ballistic.hospital.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/docType")
public class DoctorTypeController {

    @Autowired
    private DoctorTypeRepository doctorTypeRepository;
    @Autowired
    private Util util;

    @RequestMapping(value="/addDoctorType", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('DBA' ,'ADMIN')")
    public ResponseEntity<DoctorType> addDoctorType(@RequestBody String doctorType) {
        this.util.showLine();
        System.out.println("Value of doctor type"+ doctorType);
        if(doctorTypeRepository.findByType(doctorType) == null){
            if(doctorType.trim().equals("")){
                return new ResponseEntity<DoctorType>(HttpStatus.NOT_ACCEPTABLE);
            }
            DoctorType doctorType1 = new DoctorType();
            doctorType1.setType(doctorType.toUpperCase());
            doctorTypeRepository.save(doctorType1);
            return new ResponseEntity<DoctorType>(doctorType1,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<DoctorType>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @RequestMapping(value="/getAllDotorTypes", method = RequestMethod.GET)
    public ResponseEntity<List<DoctorType>> getAllDotorTypes() {

        List<DoctorType> doctorTypeList = doctorTypeRepository.findAll();
        if(doctorTypeList.isEmpty()){
            return new ResponseEntity<List<DoctorType>>(doctorTypeList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DoctorType>>(doctorTypeList, HttpStatus.OK);
    }


    @RequestMapping(value = "/deleteDoctorType/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('DBA' ,'ADMIN')")
    public ResponseEntity<DoctorType> deleteDoctorType(@PathVariable("id") Long id) {
        this.doctorTypeRepository.delete(id);
        return new ResponseEntity<DoctorType>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/getDoctorType/{id}",method = RequestMethod.GET)
    public ResponseEntity<DoctorType> getDoctorType(@PathVariable("id") Long id) {

        DoctorType doctorType = this.doctorTypeRepository.findOne(id);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.OK);
    }


    @RequestMapping(value = "/updateDoctorType/{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('DBA' ,'ADMIN')")
    public ResponseEntity<DoctorType> updateDoctorType(@RequestBody DoctorType doctorType) {
        this.doctorTypeRepository.save(doctorType);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.OK);
    }




}
