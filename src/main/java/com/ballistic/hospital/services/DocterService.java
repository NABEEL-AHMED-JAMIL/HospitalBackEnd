package com.ballistic.hospital.services;

import com.ballistic.hospital.entity.Docter;
import com.ballistic.hospital.repository.DocterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nabeel on 4/27/2017.
 */
@Service
public class DocterService  implements UserDetailsService {

    @Autowired
    DocterRepository docterRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Docter docter = docterRepository.findByUserName(s);
        if(docter != null){
            List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(docter.getRole());
            String password = docter.getPassword();

            return new org.springframework.security.core.userdetails.User(s, password, auth);
        }
        else{
            throw  new UsernameNotFoundException("No record found");
        }
    }
}
