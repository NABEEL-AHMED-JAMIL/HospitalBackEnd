package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findByUsername(String username);

    Doctor findByEmail(String email);

    Doctor findByUsernameAndEmail(String username ,String email);
}
