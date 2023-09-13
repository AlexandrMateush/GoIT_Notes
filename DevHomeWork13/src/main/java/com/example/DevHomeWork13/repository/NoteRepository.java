package com.example.DevHomeWork13.repository;

import com.example.DevHomeWork13.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {

}
