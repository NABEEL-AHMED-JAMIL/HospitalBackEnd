package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Role;
import com.ballistic.hospital.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RoleController {

    // login user
    @Autowired
    private RoleRepository roleRepository;

    // post the new doctor
    @RequestMapping(value="/getRole",  method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getRole() {

        return new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK);

    }

}
