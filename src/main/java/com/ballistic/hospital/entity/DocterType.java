package com.ballistic.hospital.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

/**
 * Created by Nabeel on 4/18/2017.
 */

@Entity
@Table(name = "docterType")

public class DocterType {


    //
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    //
    @Column(name = "type")
    private String type;

    //
    public DocterType() {
        super();
    }

    public DocterType(Long id, String type) { this.id = id; this.type = type; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "DocterType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

}
