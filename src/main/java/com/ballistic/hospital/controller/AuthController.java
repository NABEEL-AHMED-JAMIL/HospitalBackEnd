package com.ballistic.hospital.controller;

import com.ballistic.hospital.dto.DoctorTokenState;
import com.ballistic.hospital.entity.Doctor;
//import com.ballistic.hospital.dto.LoginDTO;
import com.ballistic.hospital.repository.DoctorRepository;
import com.ballistic.hospital.security.TokenHelper;
import com.ballistic.hospital.service.EmailService;
import com.ballistic.hospital.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lycus 01 on 7/4/2017.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private Util util;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EmailService emailService;


    @Autowired
    TokenHelper tokenHelper;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {

        Doctor doctor = (Doctor) authentication.getPrincipal();
        String authToken = tokenHelper.getToken( request );

        if (authToken != null && tokenHelper.canTokenBeRefreshed(authToken)) {

            String refreshedToken = tokenHelper.refreshToken(authToken);

            Cookie authCookie = new Cookie( TOKEN_COOKIE, ( refreshedToken ) );
            authCookie.setPath( "/" );
            authCookie.setHttpOnly( true );
            authCookie.setMaxAge( EXPIRES_IN );
            // Add cookie to response
            response.addCookie( authCookie );

            DoctorTokenState doctorTokenState = new DoctorTokenState(refreshedToken, doctor  ,EXPIRES_IN);
            return ResponseEntity.ok(doctorTokenState);
        } else {
            DoctorTokenState doctorTokenState = new DoctorTokenState();
            return ResponseEntity.accepted().body(doctorTokenState);
        }
    }


    //ok test call
    @RequestMapping(value="/forgotPassword",  method = RequestMethod.POST)
    public ResponseEntity<String> forgotPassword(@RequestBody String email ) {

        try {
            Doctor doctor = doctorRepository.findByEmail(email);
            if (doctor.equals(null)) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            } else {
                // check your email and click back button
                emailService.sendSimpleMessage(email ,"Login PassWord", doctor);
                return new ResponseEntity<String>(email, HttpStatus.OK);
            }
        } catch (NullPointerException e1) {
            System.out.println("Error:--> InValid Email");
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    //    //ok test call
//    @RequestMapping(value="/login",  method = RequestMethod.POST)
//    public ResponseEntity<Doctor> login(@RequestBody LoginDTO doctor ) {
//
//        Doctor doctor1 = doctorRepository.findByUserName(doctor.getUsername());
//        if(doctor1 != null){
//            // check the user true
//            if(doctor.getUsername().equals(doctor1.getUsername())){
//                if(doctor.getPassword().equals(doctor1.getPassword())){
//                    // set the null for password
//                    doctor1.setPassword(null);
//                    return new ResponseEntity<Doctor>(doctor1, HttpStatus.OK);
//                }else {
//                    return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
//                }
//
//            }else {
//                return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
//            }
//        }else{
//            System.out.println("Doctor null");
//            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
//
//        }
//    }


}
