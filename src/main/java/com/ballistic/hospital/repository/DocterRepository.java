package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Docter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Repository
public interface DocterRepository extends JpaRepository<Docter,Long> {

    Docter findByUserName(String userName);
}
