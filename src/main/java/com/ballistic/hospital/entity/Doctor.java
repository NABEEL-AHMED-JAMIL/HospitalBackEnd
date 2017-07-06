package com.ballistic.hospital.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "email", unique=true, nullable=false)
    private String email;
    @Column(name = "doctor_name", unique=true, nullable=false)
    private String userName;
    @Column(name = "passWord" , nullable = false)
    private String passWord;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "active")
    private boolean active;
    @JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    @OneToOne
    private DoctorType doctorType;

    public Doctor() {
        super();
    }

    public Doctor(Long id, String email, String userName, String passWord, String firstName, String lastName, boolean active, Set<Role> roles, DoctorType doctorType) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
        this.doctorType = doctorType;
    }

    public Doctor(Doctor doctor){
        this.id = doctor.getId();
        this.email = doctor.getEmail();
        this.userName = doctor.getUserName();
        this.passWord = doctor.getpassWord();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.active = doctor.getActive();
        this.roles = doctor.getRoles();
        this.doctorType = doctor.getDoctorType();
    }



    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getpassWord() { return passWord; }

    public void setpassWord(String password) { this.passWord = password; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public boolean getActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public DoctorType getDoctorType() { return doctorType; }

    public void setDoctorType(DoctorType doctorType) { this.doctorType = doctorType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (active != doctor.active) return false;
        if (!id.equals(doctor.id)) return false;
        if (!email.equals(doctor.email)) return false;
        if (!userName.equals(doctor.userName)) return false;
        if (!passWord.equals(doctor.passWord)) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (!roles.equals(doctor.roles)) return false;
        return doctorType.equals(doctor.doctorType);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + passWord.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + roles.hashCode();
        result = 31 * result + doctorType.hashCode();
        return result;
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
                ", active=" + active +
                ", roles=" + roles +
                ", doctorType=" + doctorType +
                '}';
    }
}
