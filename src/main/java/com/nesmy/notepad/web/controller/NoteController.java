package com.nesmy.notepad.web.controller;

import com.nesmy.notepad.models.Note;
import com.nesmy.notepad.web.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class NoteController {

    NoteService noteService;
    @GetMapping("/notes")
    public Iterable<Note> showAll(){
        return noteService.findAll();
    }

    @GetMapping("/note/{id}")
    public Optional<Note> show(@PathVariable String id){
        int noteId = Integer.parseInt(id);
        return noteService.findById(noteId);
    }

    @PostMapping("/note/search")
    public List<Note> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return noteService.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/note")
    public Note create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return noteService.save(new Note(title, content));
    }

    @PutMapping("/note/{id}")
    public Note update(@PathVariable String id, @RequestBody Map<String, String> body){
        int noteId = Integer.parseInt(id);
        // getting note
        Optional<Note> optionalNote = noteService.findById(noteId);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(body.get("title"));
            note.setContent(body.get("content"));
            return noteService.save(note);
        } else {
            // Handle the case where the note with the given ID is not found
            throw new IllegalArgumentException("Note not found with ID: " + noteId);
        }
    }

    @DeleteMapping("note/{id}")
    public boolean delete(@PathVariable String id){
        int noteId = Integer.parseInt(id);
        noteService.deleteById(noteId);
        return true;
    }
}
