package com.ballistic.hospital.entity;


import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Entity
@Table(name = "doctor")
public class Doctor extends DeletableModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique=true, nullable=false)
    private String email;
    @Column(name = "name", unique=true, nullable=false)
    private String userName;
    @Column(name = "passWord" , nullable = false)
    private String passWord;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
	@Column(name = "gender")
    private boolean gender;
    @Column(name = "active")
    private boolean active;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
            name = "doctor_roles",
            joinColumns = @JoinColumn(
                    name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    @OneToOne
    private DoctorType doctorType;

    public Doctor() {
        super();
    }

    public Doctor(Long id, String email, String userName,
                  String passWord, String firstName, String lastName,
                  boolean gender, boolean active, Set<Role> roles,
                  DoctorType doctorType) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.active = active;
        this.roles = roles;
        this.doctorType = doctorType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", active=" + active +
                ", roles=" + roles +
                ", doctorType=" + doctorType +
                '}';
    }
}
