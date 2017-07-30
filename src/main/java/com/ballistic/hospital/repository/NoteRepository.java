package com.ballistic.hospital.repository;

import com.ballistic.hospital.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

}
