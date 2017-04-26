package com.ballistic.hospital.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

    @OneToOne(targetEntity = Docter.class)
    private Docter docter;
    //
    @OneToOne(targetEntity = DocterType.class)
    private DocterType docterType;

    public Note() {
        super();
    }

    public Note(Long id, String description, Date noteDate, Docter docter, DocterType docterType) {
        this.id = id;
        this.description = description;
        this.noteDate = noteDate;
        this.docter = docter;
        this.docterType = docterType;
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

    public Docter getDocter() {
        return docter;
    }

    public void setDocter(Docter docter) {
        this.docter = docter;
    }

    public DocterType getDocterType() {
        return docterType;
    }

    public void setDocterType(DocterType docterType) {
        this.docterType = docterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (description != null ? !description.equals(note.description) : note.description != null) return false;
        if (noteDate != null ? !noteDate.equals(note.noteDate) : note.noteDate != null) return false;
        return docter != null ? docter.equals(note.docter) : note.docter == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (noteDate != null ? noteDate.hashCode() : 0);
        result = 31 * result + (docter != null ? docter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", noteDate=" + noteDate +
                ", docter=" + docter +
                ", docterType=" + docterType +
                '}';
    }
}
