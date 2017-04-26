package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Docter;
import com.ballistic.hospital.repository.DocterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nabeel on 4/15/2017.
 */
@RestController
@RequestMapping("/docter")
public class DocterController {

    // repository for user...
      @Autowired
    private DocterRepository docterRepository;

    // post the new Docter
    @RequestMapping(value="/register",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Docter> registerUser(@RequestBody Docter docter ) {

        this.docterRepository.save(docter);
        return new ResponseEntity<Docter>(docter, HttpStatus.OK);

    }

    //-------------------Retrieve All Docter's----------------
    @RequestMapping(value = "/docters" ,method = RequestMethod.GET)
    public ResponseEntity<List<Docter>> listAllDocter() {

       List<Docter> docterList = docterRepository.findAll();
        if(docterList.isEmpty()){

            return new ResponseEntity<List<Docter>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<Docter>>(docterList, HttpStatus.OK);
    }

      @RequestMapping(value="/login",  method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Docter> logInUser(@RequestBody Docter docter  ) {


          if(docter.getUserName().equals("admin") && docter.getPassword().equals("admin")){

            Docter docter1 = docterRepository.findOne((long) 1);
              System.out.println(docter1.toString());
            return new ResponseEntity<Docter>(docter1, HttpStatus.OK);
          }
        return new ResponseEntity<Docter>(HttpStatus.NOT_FOUND);

    }

    // update and delete or not used yed.....


}
