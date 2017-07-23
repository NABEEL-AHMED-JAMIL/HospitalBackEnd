package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lycus 01 on 7/22/2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}

