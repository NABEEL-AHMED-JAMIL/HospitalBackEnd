package com.ballistic.hospital.controller;

import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import com.ballistic.hospital.repository.DocterRepository;
import com.ballistic.hospital.repository.DocterTypeRepository;
import com.ballistic.hospital.repository.NoteRepository;
import com.ballistic.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nabeel on 4/16/2017.
 */
@RestController
@RequestMapping("/note")
public class
NoteController {

    Logger logger = Logger.getLogger(NoteController.class.getName());
    //
    // repository for Note...
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DocterRepository docterRepository;
    @Autowired
    private DocterTypeRepository docterTypeRepository;

    // post the new Note
    @RequestMapping(value="/addNote/{patientId}",  method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> newNote(@PathVariable Long patientId, @RequestBody Note note)  {


        Patient currentPatient = patientRepository.findOne(patientId);
        noteRepository.save(note);
        currentPatient.getNotes().add(note);
        patientRepository.save(currentPatient);

        return new ResponseEntity<Patient>( currentPatient , HttpStatus.OK);

    }


    // get the All the Note
    @RequestMapping(value="/getAllNotes", method = RequestMethod.GET)
    public ResponseEntity<List<Note>> getAllNotes() {

        List<Note> notes_list = noteRepository.findAll();
        
        if(notes_list.isEmpty()){
            return new ResponseEntity<List<Note>>(notes_list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Note>>(notes_list,HttpStatus.OK);
    }

    //  delete thec note
    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Note> DeleteNote(@PathVariable("id") Long id) {

        Note note = this.noteRepository.findOne(id);
        this.noteRepository.delete(note);
        return new ResponseEntity<Note>(note,HttpStatus.OK);
    }

    // update the note
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<Note> UpdateNote(@PathVariable("id") Long id,@RequestBody Note note) {

        //

        Note currentNote = this.noteRepository.findOne(id);
        //
        currentNote.setNoteDate(note.getNoteDate());
        currentNote.setDocterType(note.getDocterType());
        currentNote.setDescription(note.getDescription());

        this.noteRepository.save(currentNote);
        System.out.print(currentNote);

        return new ResponseEntity<Note>(currentNote,HttpStatus.OK);
    }


}
