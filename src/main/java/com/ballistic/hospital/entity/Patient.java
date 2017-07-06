package com.ballistic.hospital.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import java.util.List;


/**
 * Created by Nabeel on 4/19/2017.
 */

@Entity
@Table(name = "patient")
public class Patient{

    @Id
    @Column(name = "mr_no")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long mrNo;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @JsonManagedReference
    @OneToMany( targetEntity=Note.class)
    @JoinColumn(name="mr_no", referencedColumnName = "mr_no")
    private List<Note> notes;


    public Patient() {}

    public Patient( Long mrNo, String name, String phone) {

        this.mrNo = mrNo;
        this.name = name;
        this.phone = phone;
    }

    public Long getMrNo() {
        return mrNo;
    }

    public void setMrNo(Long mrNo) {
        this.mrNo = mrNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "mrNo=" + mrNo +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", notes=" + notes +
                '}';
    }

}
