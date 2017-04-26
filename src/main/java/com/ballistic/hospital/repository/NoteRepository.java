package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

}
