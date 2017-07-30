package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.DoctorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorTypeRepository extends JpaRepository<DoctorType,Long> {

    DoctorType findByType(String type);
}
