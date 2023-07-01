package com.nesmy.notepad.web.service;

import com.nesmy.notepad.data.NoteRepository;
import com.nesmy.notepad.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Optional<Note> findById(Integer id){
        return noteRepository.findById(id);
    }

    public Note save(Note note){
        return noteRepository.save(note);
    }

    public void deleteById(Integer id){
        noteRepository.deleteById(id);
    }

    public Iterable<Note> findAll(){
        return noteRepository.findAll();
    }

    public List<Note> findByTitleContainingOrContentContaining(String text, String textAgain){
        return noteRepository.findByTitleContainingOrContentContaining(text, textAgain);
    }
}
