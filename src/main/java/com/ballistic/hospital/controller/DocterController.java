package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Docter;
import com.ballistic.hospital.repository.DocterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Docter docter1 = docterRepository.findByUserName(docter.getUserName());

        if(docter1 != null){
            //
            return new ResponseEntity<Docter>(HttpStatus.NO_CONTENT);

        }else{
            docter.setRole("user");
            // encode the passWord and decode the passWord
            this.docterRepository.save(docter);
            return new ResponseEntity<Docter>(docter, HttpStatus.OK);
        }


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
      public ResponseEntity<Docter> logInUser(@RequestBody Docter docter) {

          Docter docter1 = docterRepository.findByUserName(docter.getUserName());
          //System.out.print(docter1.toString());
          if(docter1 == null ){
              //
              return new ResponseEntity<Docter>(HttpStatus.NOT_FOUND);

          }else{

              if(docter.getUserName().equals(docter1.getUserName()) && docter.getPassword().equals(docter1.getPassword())){

                  return new ResponseEntity<Docter>(docter1,HttpStatus.OK);

              }else {

                  return new ResponseEntity<Docter>(HttpStatus.NO_CONTENT);
              }
          }



    }




}
