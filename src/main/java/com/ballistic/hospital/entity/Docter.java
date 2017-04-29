package com.ballistic.hospital.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Entity
@Table(name = "docter")


public class Docter  {
    //
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    //
    @Column(name = "docter_name" , unique=true, nullable=false)
    private String userName;
    //
    @Column(name = "password" , nullable = false)
    private String password;
    //
    @Column(name = "first_name")
    private String firstName;
    //
    @Column(name = "last_name")
    private String lastName;
    // docter have the user rol
    @Column(name = "role", nullable = false)
    private String role;
    //
    @OneToOne
    private DocterType docterType;

    public Docter() {
        //
        super();
    }

    public Docter(Long id, String userName, String password, String firstName, String lastName, String role, DocterType docterType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.docterType = docterType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

        Docter docter = (Docter) o;

        if (!id.equals(docter.id)) return false;
        if (!userName.equals(docter.userName)) return false;
        if (!password.equals(docter.password)) return false;
        if (firstName != null ? !firstName.equals(docter.firstName) : docter.firstName != null) return false;
        if (lastName != null ? !lastName.equals(docter.lastName) : docter.lastName != null) return false;
        if (!role.equals(docter.role)) return false;
        return docterType.equals(docter.docterType);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + role.hashCode();
        result = 31 * result + docterType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Docter{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", docterType=" + docterType +
                '}';
    }
}


