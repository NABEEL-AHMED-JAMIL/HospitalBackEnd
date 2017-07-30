package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.stream.Stream;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findByUsername(String username);

    Doctor findByEmail(String email);
//    select c from Customer c where c.email = :email
//    @Query("select doctor from Doctor doctor where doctor.username = :username  || doctor.email = :email")
    Doctor findByUsernameAndEmail(String username ,String email);
}
