package com.ballistic.hospital.services;

import com.ballistic.hospital.entity.Docter;
import com.ballistic.hospital.repository.DocterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

// auth for login
/**
 * Created by Nabeel on 4/27/2017.
 */
//@Service
public class DocterDetailsService {// implements UserDetailsService {
//
//    @Autowired
//    DocterRepository docterRepository;
//
//    //login/authentication with Spring Security
//    @Override
//   @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        System.out.print(s.toString());
//        Docter docter = docterRepository.findByUserName(s);
//        System.out.print(docter);
//        if(docter != null){
//            List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(docter.getRole());
//
//            return new org.springframework.security.core.userdetails.User(docter.getUserName(), docter.getPassword(), auth);
//        }
//        else{
//            throw  new UsernameNotFoundException("No record found");
//        }
//    }

}
