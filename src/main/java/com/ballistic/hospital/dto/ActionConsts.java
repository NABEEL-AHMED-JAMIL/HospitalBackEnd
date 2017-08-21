package com.ballistic.hospital.dto;

/**
 * Created by Lycus 01 on 8/21/2017.
 */
public class ActionConsts {

    // auth urls's mapping
    public static final String AUTH = "/auth";
    public static final String LOGIN = AUTH + "/login";
    public static final String  REFRESH = "/refresh";
    public static final String LOGOUT =  AUTH + "/logout";
    public static final String REGISTER =  "/register";
    public static final String FOR_GOT_PASSWORD = "/forgotPassword";
    public static final String FETCH_REST_PASSWORD_DETAIL =  "/fetchRestPassWordDetail/{id}";
    public static final String UP_DATE_PASSWORD = "/updatePassword/{id}";

    // role url's mapping
    public static final String  GET_ROLES =  "/getRoles";
    public static final String  GET_ROLE =  "/getRole";
    public static final String  NEW_ROLE =  "/newRole";
    public static final String  UPDATE_ROLE =  "/updateRole";
    public static final String  DELETE_ROLE =  "/deleteRole";

    // doctor url"s mapping
    public static final String DOCTOR = "/doctor";
    public static final String GET_ALL_DOCTOR =  "/getAllDoctor";
    public static final String GET_DOCTOR  = "/getDoctor";

    // doctor type url's mapping
    public static final String DOC_TYPE = "/docType";
    public static final String ADD_DOCTOR_TYPE =  "/addDoctorType";
    public static final String GET_ALL_DOCTOR_TYPES =  "/getAllDotorTypes";
    public static final String DELETE_DOCTOR_TYPE =  "/deleteDoctorType/{id}";
    public static final String GET_DOCTOR_TYPE =  "/getDoctorType/{id}";
    public static final String UPDATE_DOCTOR_TYPE =  "/updateDoctorType/{id}";

    // note url's mapping
    public static final String NOTE = "/note";
    public static final String NEW_NOTE = "/newNote/{patientId}";
    public static final String GET_ALL_NOTES = "/getAllNotes";
    public static final String DELETE_NOTE = "/deleteNote/{id}";
    public static final String UPDATE_NOTE = "/updateNote/{id}";

    // patient url's
    public static final String PATIENT = "/patient";
    public static final String NEW_PATIENT = "/newPatient";
    public static final String GET_ALL_PATIENT = "/getAllPatient";
    public static final String GET_ALL_PATIENT_NOTES = "/getAllPatientNotes/{mrNo}";
    public static final String GET_PATIENT = "/getPatient/{mrNo}";
    public static final String DELETE_PATIENT = "/deletePatient/{mrNo}";
    public static final String UPDATE_PATIENT = "/updatePatient/{mrNo}";


}
