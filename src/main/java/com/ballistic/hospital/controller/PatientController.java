package com.ballistic.hospital.controller;

import com.ballistic.hospital.dto.PatientDTO;
import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import com.ballistic.hospital.repository.DoctorTypeRepository;
import com.ballistic.hospital.repository.NoteRepository;
import com.ballistic.hospital.repository.PatientRepository;
import com.ballistic.hospital.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorTypeRepository doctorTypeRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private Util util;

    // ok test call
    @RequestMapping(value="/newPatient",  method = RequestMethod.POST)
    @PreAuthorize("hasRole('DBA') or hasRole('ADMIN')")
    public ResponseEntity<Patient> newPatient(@RequestBody PatientDTO patientDTO ) {

        try {
            // check-point
            Patient patient = new Patient();
            // if patient name null then used the condition
            if(patientDTO.getName() == null){
                // return error message
                return new ResponseEntity<Patient>(HttpStatus.NOT_ACCEPTABLE);
            }
            this.util.showLine();
            patient.setName(patientDTO.getName());
            patient.setPhone(patientDTO.getPhone());
            patient.setAge(patientDTO.getAge());
            patient.setNotes(null);
            System.out.println(patient.toString());
            this.util.showLine();
            this.patientRepository.save(patient);
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);
        }catch (NullPointerException e){
            e.printStackTrace();
            return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
        }
    }

    // ok test call
    @RequestMapping(value="/getAllPatient", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients() {
        this.util.showLine();
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList.toArray());
        this.util.showLine();
        return new ResponseEntity<List<Patient>>(patientList,HttpStatus.OK);
    }


    // need polish
    @RequestMapping(value = "/getAllPatientNote/{mrNo}",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getAllPatientNote(@PathVariable("mrNo") Long mrNo) {

        Patient patient = patientRepository.findOne(mrNo);
        List<Note> noteList = patient.getNotes();
        List<Object> temp = new  ArrayList<Object>();
        if(noteList != null){
            // DTO alter native used can be
            for (Note note: noteList) {
                this.util.showLine();
                System.out.println(note);
                this.util.showLine();
                Map notesMap = new HashMap();
                notesMap.put("patientMrNo" , patient.getMrNo());
                notesMap.put("patientName" , patient.getName());
                notesMap.put("noteId" , note.getId());
                notesMap.put("noteDate" , note.getNoteDate());
                notesMap.put("doctorName" , note.getDoctor().getUsername());
                notesMap.put("description", note.getDescription());
                notesMap.put("noteType" , note.getDoctorType().getType());
                temp.add(notesMap);
            }
            this.util.showLine();
            System.out.println(temp.toArray());
            this.util.showLine();
            return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
        }
    }

    //
    @RequestMapping(value = "/getPatient/{mrNo}",method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable("mrNo") Long mrNo) {
        this.util.showLine();
        Patient patient = patientRepository.findOne(mrNo);
        System.out.println(patient.toString());
        this.util.showLine();
        return new ResponseEntity<Patient>(patient,HttpStatus.OK);
    }


    // ok test
    @RequestMapping(value = "/deletePatient/{mrNo}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('DBA') or hasRole('ADMIN')")
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


    @RequestMapping(value = "/updatePatient/{mrNo}",method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DBA') or hasRole('ADMIN')")
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
