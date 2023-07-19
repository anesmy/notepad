package com.nesmy.notepad.web.service;

import com.nesmy.notepad.data.NoteRepository;
import com.nesmy.notepad.models.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{

    private NoteRepository noteRepository;

    @Override
    public Note findById(Integer id){
        Optional<Note> note = noteRepository.findById(id);
        return note.orElse(null);
    }

    @Override
    public Note save(Note note){
        if (note == null) return null;
        return noteRepository.save(note);
    }

    @Override
    public boolean deleteById(Integer id){
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            noteRepository.delete(note.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Note> findAll(){
        return noteRepository.findAll();
    }

    @Override
    public List<Note> findByTitleContainingOrContentContaining(String text, String textAgain){
        return noteRepository.findByTitleContainingOrContentContaining(text, textAgain);
    }
}
