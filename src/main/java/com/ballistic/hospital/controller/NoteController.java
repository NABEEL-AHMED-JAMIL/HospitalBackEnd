package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import com.ballistic.hospital.repository.DoctorRepository;
import com.ballistic.hospital.repository.DoctorTypeRepository;
import com.ballistic.hospital.repository.NoteRepository;
import com.ballistic.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.ballistic.hospital.dto.ActionConsts.*;


@RestController
@RequestMapping(NOTE)
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorTypeRepository doctorTypeRepository;


    @RequestMapping(value = NEW_NOTE, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_DBA' ,'ROLE_ADMIN')")
    public ResponseEntity<Patient> newNote(@PathVariable Long patientId, @RequestBody Note note)  {

        Patient currentPatient = patientRepository.findOne(patientId);
        noteRepository.save(note);
        currentPatient.getNotes().add(note);
        patientRepository.save(currentPatient);
        return new ResponseEntity<Patient>( currentPatient , HttpStatus.OK);

    }


    @RequestMapping(value = GET_ALL_NOTES, method = RequestMethod.GET)
    ResponseEntity<List<Note>> getAllNotes() {

        List<Note> notes_list = noteRepository.findAll();
        if(notes_list.isEmpty()){
            return new ResponseEntity<List<Note>>(notes_list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Note>>(notes_list,HttpStatus.OK);
    }

    @RequestMapping(value = DELETE_NOTE, method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ROLE_DBA' ,'ROLE_ADMIN')")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id) {

        Note note = this.noteRepository.findOne(id);
        this.noteRepository.delete(note);
        return new ResponseEntity<Note>(note,HttpStatus.OK);
    }

    @RequestMapping(value = UPDATE_NOTE, method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_DBA' ,'ROLE_ADMIN')")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id,@RequestBody Note note) {

        Note currentNote = this.noteRepository.findOne(id);
        currentNote.setNoteDate(note.getNoteDate());
        currentNote.setDoctorType(note.getDoctorType());
        currentNote.setDescription(note.getDescription());
        this.noteRepository.save(currentNote);
        System.out.print(currentNote);

        return new ResponseEntity<Note>(currentNote,HttpStatus.OK);

    }

}
