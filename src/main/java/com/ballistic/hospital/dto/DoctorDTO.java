package com.ballistic.hospital.dto;

/**
 * Created by Lycus 01 on 7/16/2017.
 */
// used for the login
public class DoctorDTO {

    private String username;
    private String password;

    public DoctorDTO() {}

    public DoctorDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
