package com.nesmy.notepad.web.controller;

import com.nesmy.notepad.data.NoteRepository;
import com.nesmy.notepad.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/note")
    public Iterable<Note> index(){
        return noteRepository.findAll();
    }

    @GetMapping("/note/{id}")
    public Optional<Note> show(@PathVariable String id){
        int noteId = Integer.parseInt(id);
        //Note note = noteRepository.findOne(noteId);
        return noteRepository.findById(noteId);
    }

    @PostMapping("/note/search")
    public List<Note> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return noteRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/note")
    public Note create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return noteRepository.save(new Note(title, content));
    }

    @PutMapping("/note/{id}")
    public Note update(@PathVariable String id, @RequestBody Map<String, String> body){
        int noteId = Integer.parseInt(id);
        // getting note
        Note note = noteRepository.findOne(noteId);
        note.setTitle(body.get("title"));
        note.setContent(body.get("content"));
        return noteRepository.save(note);
    }

    @DeleteMapping("note/{id}")
    public boolean delete(@PathVariable String id){
        int noteId = Integer.parseInt(id);
        noteRepository.deleteById(noteId);
        return true;
    }
}
