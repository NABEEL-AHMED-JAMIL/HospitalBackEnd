package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.DocterType;
import com.ballistic.hospital.repository.DocterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nabeel on 4/19/2017.
 */
@RestController
@RequestMapping("/docType")
public class DocterTypeController {
    // this is used
    @Autowired
    private DocterTypeRepository docterTypeRepository;

    // post the new DocterType
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DocterType> AddDocterType(@RequestBody DocterType docterType) {

        this.docterTypeRepository.save(docterType);
        return new ResponseEntity<DocterType>(docterType,HttpStatus.CREATED);
    }

    // get the All the DocterType
    @RequestMapping(value="/getAllType", method = RequestMethod.GET)
    public ResponseEntity<List<DocterType>> getAllTypes() {

        List<DocterType> docterTypeList = docterTypeRepository.findAll();

        if(docterTypeList.isEmpty()){
            return new ResponseEntity<List<DocterType>>(docterTypeList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DocterType>>(docterTypeList, HttpStatus.OK);
    }


    //  delete thec Docter type
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<DocterType> DeleteDocterType(@PathVariable("id") Long id) {

        this.docterTypeRepository.delete(id);
        return new ResponseEntity<DocterType>(HttpStatus.NO_CONTENT);
    }

    // get docter type by id
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<DocterType> getDocterType(@PathVariable("id") Long id) {

        DocterType docterType = this.docterTypeRepository.findOne(id);
        return new ResponseEntity<DocterType>(docterType,HttpStatus.OK);
    }
    // update the DocterType
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<DocterType> UpdateDocterType(@RequestBody DocterType docterType) {

        this.docterTypeRepository.save(docterType);
        return new ResponseEntity<DocterType>(docterType,HttpStatus.OK);
    }

}
