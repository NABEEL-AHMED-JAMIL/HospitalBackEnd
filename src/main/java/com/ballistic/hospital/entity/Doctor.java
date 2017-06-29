package com.ballistic.hospital.entity;


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
    private Long id;
    @Column(name = "doctor_name" , unique=true, nullable=false)
    private String userName;
    @Column(name = "password" , nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    // doctor have the user rol
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToOne
    private DoctorType doctorType;

    public Doctor() {
        super();
    }

    public Doctor(Long id, String userName, String password, String firstName, String lastName, Set<Role> roles, DoctorType doctorType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.doctorType = doctorType;
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

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (!id.equals(doctor.id)) return false;
        if (!userName.equals(doctor.userName)) return false;
        if (!password.equals(doctor.password)) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (!roles.equals(doctor.roles)) return false;
        return doctorType.equals(doctor.doctorType);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + roles.hashCode();
        result = 31 * result + doctorType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + roles + '\'' +
                ", doctorType=" + doctorType +
                '}';
    }
}
