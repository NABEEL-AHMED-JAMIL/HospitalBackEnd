package com.ballistic.hospital.services;

import com.ballistic.hospital.entity.Docter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.alps.Doc;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Nabeel on 4/28/2017.
 */
public class DocterValidator { //implements Validator {

//    @Autowired
//    private DocterService docterService;
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Docter.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        //
//        Docter docter = (Docter) o;
//        //
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
//        if (docter.getUserName().length() < 6 || docter.getUserName().length() > 32) {
//            errors.rejectValue("username", "Size.userForm.username");
//        }
//        if (docterService.findByUsername(docter.getUserName()) != null) {
//            errors.rejectValue("username", "Duplicate.userForm.username");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//        if (docter.getPassword().length() < 8 || docter.getPassword().length() > 32) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }

//        if (!docter.getPasswordConfirm().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }

    //}
}
