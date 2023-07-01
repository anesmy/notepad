package com.nesmy.notepad.web.service;

import com.nesmy.notepad.models.Note;

import java.util.List;

public interface NoteService {

    Note findById(Integer id);

    Note save(Note note);

    void deleteById(Integer id);

    List<Note> findAll();

    List<Note> findByTitleContainingOrContentContaining(String text, String textAgain);
}
