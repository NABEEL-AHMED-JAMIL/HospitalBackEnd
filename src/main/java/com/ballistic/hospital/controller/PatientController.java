package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import com.ballistic.hospital.repository.DoctorTypeRepository;
import com.ballistic.hospital.repository.NoteRepository;
import com.ballistic.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by Nabeel on 4/19/2017.
 */

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorTypeRepository doctorTypeRepository;
    @Autowired
    private NoteRepository noteRepository;


    @RequestMapping(value="/addPatient",  method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DBA\",\"USER\")")
    public ResponseEntity<Patient> newPatient(@RequestBody Patient patient ) {
        this.patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);

    }

    @RequestMapping(value="/getAllPatient", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DBA\",\"USER\")")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patientList = patientRepository.findAll();

        return new ResponseEntity<List<Patient>>(patientList,HttpStatus.OK);
    }


    @RequestMapping(value = "notes/{mrNo}",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DBA\",\"USER\")")
    public ResponseEntity<List<Object>> getAllPatientNote(@PathVariable("mrNo") Long mrNo) {

         Patient patient = patientRepository.findOne(mrNo);
         List<Note> noteList = patient.getNotes();
         List<Object> temp = new  ArrayList<Object>();
         if(noteList != null){

             for (Note note: noteList) {
                 Map notesMap = new HashMap();
                 notesMap.put("patientMrNo" , patient.getMrNo());
                 notesMap.put("patientName" , patient.getName());
                 notesMap.put("noteId" , note.getId());
                 notesMap.put("noteDate" , note.getNoteDate());
                 notesMap.put("doctorName" , note.getDoctor().getUserName());
                 notesMap.put("description", note.getDescription());
                 notesMap.put("noteType" , note.getDoctorType().getType());
                 temp.add(notesMap);
             }
             return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
         }else{
             return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
         }
    }


    @RequestMapping(value = "/{mrNo}",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DBA\",\"USER\")")
    public ResponseEntity<Patient> getPatient(@PathVariable("mrNo") Long mrNo) {

        Patient patient = patientRepository.findOne(mrNo);
        return new ResponseEntity<Patient>(patient,HttpStatus.OK);
    }


    @RequestMapping(value = "/{mrNo}",method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("mrNo") Long mrNo) {

        Patient patient = this.patientRepository.findOne(mrNo);
        if(patient == null){
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);
        }else {
            this.noteRepository.delete(patient.getNotes());
            this.patientRepository.delete(mrNo);
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);

        }
    }


    @RequestMapping(value = "/{mrNo}",method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"DBA\",\"USER\")")
    public ResponseEntity<Patient> updatePatient(@PathVariable("mrNo") long mrNo, @RequestBody Patient patient) {

        Patient temp = this.patientRepository.findOne(mrNo);
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
