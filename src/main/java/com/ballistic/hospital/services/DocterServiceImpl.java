package com.ballistic.hospital.services;

import com.ballistic.hospital.entity.Docter;
import com.ballistic.hospital.repository.DocterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Nabeel on 4/28/2017.
 */
public class DocterServiceImpl { //implements DocterService {
//
//    @Autowired
//    private DocterRepository docterRepository;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    // register
//    @Override
//    public void save(Docter docter) {
//        //
//        docter.setPassword(bCryptPasswordEncoder.encode(docter.getPassword()));
//        docter.setRole("user");
//        docterRepository.save(docter);
//
//    }
//
//    // docter login
//    @Override
//    public Docter findByUsername(String username) {
//        return docterRepository.findByUserName(username);
//    }
}
