package com.ballistic.hospital.entity;


import javax.persistence.*;


@Entity
@Table(name = "doctorType")
public class DoctorType {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "type", unique = true, nullable = false)
    private String type;


    public DoctorType() {
        super();
    }

    public DoctorType(Long id, String type) { this.id = id; this.type = type; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "DoctorType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

}
