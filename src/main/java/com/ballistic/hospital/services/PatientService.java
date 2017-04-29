package com.ballistic.hospital.services;

import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import com.ballistic.hospital.repository.DocterTypeRepository;
import com.ballistic.hospital.repository.NoteRepository;
import com.ballistic.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nabeel on 4/24/2017.
 */
@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DocterTypeRepository docterTypeRepository;
    @Autowired
    private NoteRepository noteRepository;


    public Note addNoteToPatient(Long patientId, Note note){
        // save to the note
        note = noteRepository.save(note);

        // add this note to the patient
        Patient currentPatient = patientRepository.findOne(patientId);
        currentPatient.getNotes().add(note);
        // update the current patient

        patientRepository.save(currentPatient);
        // send the current patient and it's note
        currentPatient = patientRepository.getOne(patientId);
        return note;
    }
}
