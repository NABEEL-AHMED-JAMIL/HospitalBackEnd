package com.ballistic.hospital.dto;

/**
 * Created by Lycus 01 on 7/21/2017.
 */
public class PatientDTO {

    private String name;
    private String phone;
    private Long age;


    public PatientDTO() { super(); }

    public PatientDTO(String name, String phone, Long age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
