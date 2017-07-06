package com.ballistic.hospital.security;

import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.entity.Role;
import com.ballistic.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Nabeel-Ahmed 01 on 7/2/2017.
 */
@Configuration
@Service("CustomDoctorDetailsService")
public class CustomDoctorDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("----------------------------------------");
        System.out.println("-------"+username+"---------------------");
        Doctor doctor = doctorRepository.findByUserName(username);
        // getting the role

        System.out.println(doctor.toString());
        System.out.println(doctor.getId()+"--------"+doctor.getpassWord()+"------"+doctor.getUserName()+"----------"+doctor.getRoles());
        System.out.println("------------------End------------------");

        if(doctor != null) {
            System.out.println("------------------doctor not null------------------");
            return new UserDetails()  {

                @Override
                public boolean isEnabled() {
                    System.out.println("------------------Enable is true------------------");
                    return doctor.getActive();
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    System.out.println("------------------CredentialsNonExpired is true------------------");
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    System.out.println("------------------doctorNonLocked is true------------------");
                    return true;
                }

                @Override
                public boolean isAccountNonExpired() {
                    System.out.println("------------------doctorNonExpired is true------------------");
                    return true;
                }

                @Override
                public String getUsername() {
                    System.out.println("------------------Username is getting------------------");
                    return doctor.getUserName();
                }

                @Override
                public String getPassword() {
                    System.out.println("------------------PassWord is getting------------------");
                    return null;
                }

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    System.out.println("------------------InSide the Authorities------------------");
                    System.out.println("----------------------------------------");
                    int i = 0;
                    Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
                    System.out.println("----------Checking the auth set size ---------"+auths.size()+"----------------");
                    for(Role role: doctor.getRoles()){
                        System.out.println("---------------"+role+"-------------"+ i++ +"------------");
                        auths.add(new SimpleGrantedAuthority(role.getRole()));
                        System.out.println("---------------"+auths.toString()+"-------------"+ auths.size() +"------------");
                    }
                    return new ArrayList<GrantedAuthority>(auths);

                }
            };

        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }

}
