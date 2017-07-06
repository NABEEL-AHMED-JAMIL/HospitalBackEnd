package com.ballistic.hospital.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lycus 01 on 7/2/2017.
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value="/getPakistan")
    public String newPatient() {

        return "Pakistan zindabad";
    }
}
