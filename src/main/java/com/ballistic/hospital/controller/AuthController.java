package com.ballistic.hospital.controller;

import com.ballistic.hospital.dto.DoctorTokenState;
import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.repository.DoctorRepository;
import com.ballistic.hospital.security.TokenHelper;
import com.ballistic.hospital.service.EmailService;
import com.ballistic.hospital.service.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import static com.ballistic.hospital.dto.ActionConsts.*;


@RestController
@RequestMapping(AUTH)
public class AuthController {

    @Autowired
    private Util util;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenHelper tokenHelper;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;


    @RequestMapping(value = REFRESH, method = RequestMethod.GET)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {

        try {

            Doctor doctor = (Doctor) authentication.getPrincipal();
            String authToken = tokenHelper.getToken( request );
            if (authToken != null && tokenHelper.canTokenBeRefreshed(authToken)) {

                String refreshedToken = tokenHelper.refreshToken(authToken);
                Cookie authCookie = new Cookie( TOKEN_COOKIE, ( refreshedToken ) );
                authCookie.setPath( "/" );
                authCookie.setHttpOnly( true );
                authCookie.setMaxAge( EXPIRES_IN );
                response.addCookie( authCookie );
                DoctorTokenState doctorTokenState = new DoctorTokenState(refreshedToken, doctor  ,EXPIRES_IN);
                return ResponseEntity.ok(doctorTokenState);
            } else {
                // scope not pass here
                System.out.println("DOCTER ELSE ERROR------------------------> Handle");
                DoctorTokenState doctorTokenState = new DoctorTokenState();
                System.out.println("DOCTER ELSE------------------------> Handle");
                return ResponseEntity.accepted().body(doctorTokenState);
            }
        }catch (NullPointerException e){
            System.out.println("ERROR------------------------> Handle");
            DoctorTokenState doctorTokenState = new DoctorTokenState();
            System.out.println("DOCTER ELSE------------------------> Handle");
            return ResponseEntity.accepted().body(doctorTokenState);
        }
    }


    //ok test call
    @RequestMapping(value= FOR_GOT_PASSWORD,  method = RequestMethod.POST)
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

    //ok test call
    @RequestMapping(value= FETCH_REST_PASSWORD_DETAIL,  method = RequestMethod.GET)
    public ResponseEntity<Map> fetchRestPassWordDetail(@PathVariable("id") Long id ) {

        Doctor doctor1 = this.doctorRepository.findOne(id);
        if(doctor1 != null){
            // check the user true
            Map notesMap = new HashMap();
            notesMap.put("email" , doctor1.getEmail());
            notesMap.put("username" , doctor1.getUsername());

            return new ResponseEntity<Map>(notesMap, HttpStatus.OK);
        }else{
            System.out.println("Doctor null");
            return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
        }
    }

    //ok test call
    @RequestMapping(value= UP_DATE_PASSWORD,  method = RequestMethod.PUT)
    public ResponseEntity<String> updatePassword(@PathVariable("id") Long id , @RequestBody String password) {

        Doctor doctor1 = this.doctorRepository.findOne(id);
        this.util.showLine();
        if(doctor1 != null){
            // check the user true
            doctor1.setPassword(passwordEncoder.encode(password));
            this.doctorRepository.save(doctor1);
            return new ResponseEntity<String>("PassWord Set",HttpStatus.OK);
        }else{
            System.out.println("Doctor null");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }


}
