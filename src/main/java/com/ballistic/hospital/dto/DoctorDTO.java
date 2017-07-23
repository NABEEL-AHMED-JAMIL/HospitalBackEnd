package com.ballistic.hospital.dto;

/**
 * Created by Lycus 01 on 7/16/2017.
 */
// used for the login
public class DoctorDTO {

    private String userName;
    private String passWord;

    public DoctorDTO() {}

    public DoctorDTO(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassWord() { return passWord; }

    public void setPassWord(String passWord) { this.passWord = passWord; }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
