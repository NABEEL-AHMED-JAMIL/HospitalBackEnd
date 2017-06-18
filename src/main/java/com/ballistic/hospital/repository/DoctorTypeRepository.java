package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.DoctorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nabeel on 4/19/2017.
 */
@Repository
public interface DoctorTypeRepository extends JpaRepository<DoctorType,Long> {
}
