package com.ballistic.hospital.dto;

import com.ballistic.hospital.entity.Doctor;

// this class same like a dto
public class DoctorTokenState {
    //
    private String access_token;
    private Integer expires_in;
    private Doctor doctor;

    public DoctorTokenState() {
        this.access_token = null;
        this.expires_in = null;
    }

    public DoctorTokenState(String access_token, Doctor doctor, Integer expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.doctor = doctor;
    }

    public String getAccess_token() { return access_token; }

    public void setAccess_token(String access_token) { this.access_token = access_token; }

    public Integer getExpires_in() { return expires_in; }

    public void setExpires_in(Integer expires_in) { this.expires_in = expires_in; }

    public Doctor getDoctor() { return doctor; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    @Override
    public String toString() {
        return "DoctorTokenState{" + "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in + ", doctor=" + doctor +  '}';
    }
}
