package com.ballistic.hospital.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Entity
@Table(name = "note")

public class Note {

    //
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    //

    @Column(name = "note" ,columnDefinition = "longtext")
    private String description;
    //
    @Column(name = "note_date")
    private Date noteDate;
    //

    @OneToOne(targetEntity = Doctor.class)
    private Doctor doctor;
    //
    @OneToOne(targetEntity = DoctorType.class)
    private DoctorType doctorType;

    public Note() {
        super();
    }

    public Note(Long id, String description, Date noteDate, Doctor doctor, DoctorType doctorType) {
        this.id = id;
        this.description = description;
        this.noteDate = noteDate;
        this.doctor = doctor;
        this.doctorType = doctorType;
    }

    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (description != null ? !description.equals(note.description) : note.description != null) return false;
        if (noteDate != null ? !noteDate.equals(note.noteDate) : note.noteDate != null) return false;
        return doctor != null ? doctor.equals(note.doctor) : note.doctor == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (noteDate != null ? noteDate.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", noteDate=" + noteDate +
                ", doctor=" + doctor +
                ", doctorType=" + doctorType +
                '}';
    }
}
