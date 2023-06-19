package com.nesmy.notepad.data;

import com.nesmy.notepad.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByTitleContainingOrContentContaining(String text, String textAgain);

    Note findOne(int noteID);
}
