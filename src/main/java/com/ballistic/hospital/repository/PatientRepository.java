package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Note;
import com.ballistic.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Nabeel on 4/19/2017.
 */
public interface PatientRepository extends JpaRepository<Patient,Long> {


}
