package com.ballistic.hospital.service;

import com.ballistic.hospital.entity.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

/**
 * Created by Lycus 01 on 7/23/2017.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;
    @Autowired
    private Util util;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void sendSimpleMessage(
            String to, String subject, Doctor doctor) {
        try {
            util.showLine();
            SimpleMailMessage message = new SimpleMailMessage();
//            String list[] = {"nabeel.amd93@gmail.com", "abubakar.zafar77@gmail.com", "dauood@live.co.uk"};
            message.setTo(to);
            message.setSubject("User Info");
            message.setText(message(doctor));
            sender.send(message);
            util.showLine();

        }catch (MailException exception){
            util.showLine();
            System.out.println(exception);
            util.showLine();
        }
    }

       public String message(Doctor doctor){
           String message = "Dear, "+doctor.getFirstname()+" "+doctor.getLastname()+"\n" +
                   "Welcome!\n" +
                   "Thank you for becoming a registered member of:\n" +
                   "MEDICSi Patient Portal\n" +
                   "Check out these storage related information.    \n" +
                   "**** UserName:"+ doctor.getUsername() +" and PassWord:"+ passwordEncoder.encode(doctor.getPassword()) + " and Mail account: " + doctor.getEmail() + "****.\n" +
                   "Thanks!\n" +
                   "    Your medicsi786@gmail.com";
           System.out.println(message.toString());
           return message;
       }

    //    public String composeMessage(){
//        String message = "";
//        for (String mess: readingFile()) {
//            message += mess+"\n";
//        }
//        message.replace("Nabeel Ahmed","Abubakar Zafar");
//        message.replace("Nabeel","abubakar.zafar77@gmail.com");
//        message.replace("ballistic","ballistic");
//        message.replace("nabeel.amd93@gmail.com","abubakar.zafar77@gmail.com");
//        return message;
//    }

//    public List<String> readingFile(){
//
//        try {
//            this.util.showLine();
//            System.out.println("Try ing to finding the file");
//            File file = new ClassPathResource("mail.txt").getFile();
//            System.out.println("File was found");
//            this.util.showLine();
//            System.out.println("getting fetch the file process");
//            Scanner input = new Scanner(file);
//            List<String> lines = new ArrayList<String>();
//
//            while (input.hasNextLine()) {
//                 lines.add(input.nextLine());
//            }
//            System.out.println(lines.toString());
//            input.close();
//            return lines;
//        } catch (IOException e) {
//            System.out.println("File not found");
//            e.printStackTrace();
//            this.util.showLine();
//            return null;
//        }
//    }
}
