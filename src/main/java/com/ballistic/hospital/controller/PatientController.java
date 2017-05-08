package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import com.ballistic.hospital.repository.DocterTypeRepository;
import com.ballistic.hospital.repository.NoteRepository;
import com.ballistic.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;


/**
 * Created by Nabeel on 4/19/2017.
 */

@RestController
@RequestMapping("/patient")
public class PatientController {

    Logger logger = Logger.getLogger(Patient.class.getName());

    // repository for Patient...
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DocterTypeRepository docterTypeRepository;
    //
    @Autowired
    private NoteRepository noteRepository;
    // post the new Note
    @RequestMapping(value="/addPatient",  method = RequestMethod.POST)
    public ResponseEntity<Patient> newPatient(@RequestBody Patient patient ) {
        logger.info("addPatient");
        this.patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);

    }
    // this is use
    // get the All the Patient
    @RequestMapping(value="/getAllPatient", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("getAllPatient");
        List<Patient> patientList = patientRepository.findAll();

        return new ResponseEntity<List<Patient>>(patientList,HttpStatus.OK);
    }


    //  Docter Name , Note Type , Note , Note Date
    // get the All patient note by mr_no
     @RequestMapping(value = "notes/{mrNo}",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getAllPatientNote(@PathVariable("mrNo") Long mrNo) {
         // get the patient
         Patient patient = patientRepository.findOne(mrNo);
         // get the all notes of the patient
         List<Note> noteList = patient.getNotes();
         // used this to forward the all note after getting few info
         List<Object> temp = new  ArrayList<Object>();
         if(noteList != null){
             // if the note list is not empty then this will work
             //
             for (Note note: noteList) {
                 // parse the data and then send into the new json from array to the frontend
                 Map notesMap = new HashMap();
                 notesMap.put("patientMrNo" , patient.getMrNo());
                 notesMap.put("patientName" , patient.getName());
                 notesMap.put("noteId" , note.getId());
                 notesMap.put("noteDate" , note.getNoteDate());
                 notesMap.put("docterName" , note.getDocter().getUserName());
                 notesMap.put("description", note.getDescription());
                 notesMap.put("noteType" , note.getDocterType().getType());
                 temp.add(notesMap);
             }

             return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
         }else{

             return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
         }
    }

    @RequestMapping(value = "/{mrNo}",method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable("mrNo") Long mrNo) {
        logger.info("getPatient");
        Patient patient = patientRepository.findOne(mrNo);
        return new ResponseEntity<Patient>(patient,HttpStatus.OK);
    }

    //  delete thec Patient
    @RequestMapping(value = "/{mrNo}",method = RequestMethod.DELETE)
    public ResponseEntity<Patient> DeletePatient(@PathVariable("mrNo") Long mrNo) {

        // first remove the all note than delte

        Patient patient = this.patientRepository.findOne(mrNo);
        if(patient == null){
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);
        }else {

            this.noteRepository.delete(patient.getNotes());
            this.patientRepository.delete(mrNo);
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);

        }
    }

    // update the
    @RequestMapping(value = "/{mrNo}",method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> UpdatePatient(@PathVariable("mrNo") long mrNo, @RequestBody Patient patient) {

        Patient temp = this.patientRepository.findOne(mrNo);
        //
        if(temp == null){
            return new ResponseEntity("Unable to upate. User with id " + mrNo + " not found.", HttpStatus.NOT_FOUND);
        }else{
            temp.setName(patient.getName());
            temp.setPhone(patient.getPhone());
            this.patientRepository.save(temp);
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);

        }

    }


}
