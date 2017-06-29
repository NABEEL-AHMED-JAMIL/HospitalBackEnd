package com.ballistic.hospital.dto;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lycus 01 on 6/29/2017.
 */
public class DoctorDetailDTO implements UserDetails {

    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public DoctorDetailDTO(Doctor byDoctorname) {
        this.userName = byDoctorname.getUserName();
        this.password = byDoctorname.getPassword();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : byDoctorname.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
        }
        this.authorities = grantedAuthorities;

        return;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}