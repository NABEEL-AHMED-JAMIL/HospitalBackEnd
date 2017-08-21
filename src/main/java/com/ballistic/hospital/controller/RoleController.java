package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Role;
import com.ballistic.hospital.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static com.ballistic.hospital.dto.ActionConsts.*;
import java.util.List;


@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = GET_ROLES,  method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getRoles() {

        return new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK);

    }

    // NOTE: POST, PUT, DELETE, ROLE NOT BE USED IN THE PROJECT NOW MAY USED ON DEMAND
    @RequestMapping(value = GET_ROLE,  method = RequestMethod.GET)
    public ResponseEntity<Role> getRole() {
        return new ResponseEntity<Role>(new Role() , HttpStatus.OK);
    }

    @RequestMapping(value = NEW_ROLE,  method = RequestMethod.POST)
    public ResponseEntity<Role> newRole() {
        return new ResponseEntity<Role>(new Role(), HttpStatus.OK);
    }

    @RequestMapping(value = UPDATE_ROLE,  method = RequestMethod.PUT)
    public ResponseEntity<Role> updateRole() {
        return new ResponseEntity<Role>( new Role(), HttpStatus.OK);
    }

    @RequestMapping(value = DELETE_ROLE,  method = RequestMethod.DELETE)
    public ResponseEntity<Role> deleteRole() {
        return new ResponseEntity<Role>(new Role(), HttpStatus.OK);
    }

}
