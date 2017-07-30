package com.ballistic.hospital.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "doctor")
public class Doctor extends DeletableModel implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique=true, nullable=false)
    private String email;
    @Column(name = "username", unique=true, nullable=false)
    private String username;
    @Column(name = "password" , nullable = false)
    private String password;
    // this not need for in side the db
    private String confirmPassword;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
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

    public Doctor(Long id, String email, String username, String password, String firstname,
                  String lastname, boolean gender, boolean active, Set<Role> roles, DoctorType doctorType) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.active = active;
        this.roles = roles;
        this.doctorType = doctorType;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) {this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public boolean isGender() { return gender;}

    public void setGender(boolean gender) { this.gender = gender; }

    @JsonIgnore
    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    @JsonIgnore
    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public DoctorType getDoctorType() { return doctorType; }

    public void setDoctorType(DoctorType doctorType) { this.doctorType = doctorType; }

    @JsonIgnore
    public String getConfirmPassword() { return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return roles; }

    @JsonIgnore
    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { return true; }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() { return true;}

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @JsonIgnore
    @Override
    public boolean isEnabled() { return isActive(); }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", active=" + active +
                ", roles=" + roles +
                ", doctorType=" + doctorType +
                '}';
    }
}
