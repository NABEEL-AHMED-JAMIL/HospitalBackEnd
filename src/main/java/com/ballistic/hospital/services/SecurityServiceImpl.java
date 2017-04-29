package com.ballistic.hospital.services;

import com.ballistic.hospital.entity.Docter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Nabeel on 4/28/2017.
 */
// this is used to provide the current login user
// and the auto login user after the new register in
//@Service
public class SecurityServiceImpl { //implements SecurityService{
//    //
//    @Autowired
//    private AuthenticationManager authenticationManagert;
//    // this will give us the Docter Service
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
//    //
//    // login user
//    @Override
//    public String findLoggedInUsername(){
//        //
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        //
//        if(userDetails instanceof UserDetails){
//            return ((Docter)userDetails).getUserName();
//        }
//
//        return null;
//    }
//
//    // auto login after the register
//    @Override
//    public void autologin(String username, String password) {


   // }


}
