package com.nesmy.notepad.web.controller;

import com.nesmy.notepad.models.Note;
import com.nesmy.notepad.web.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class NoteController {

    private NoteService noteServiceImpl;
    @GetMapping("/")
    public List<Note> showAll(){
        return noteServiceImpl.findAll();
    }

    @GetMapping("/note/{id}")
    public Note show(@PathVariable Integer id){
        return noteServiceImpl.findById(id);
    }

    @PostMapping("/note/search")
    public List<Note> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return noteServiceImpl.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/note")
    public Note create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return noteServiceImpl.save(new Note(title, content));
    }

    @PutMapping("/note/{id}")
    public Note update(@PathVariable String id, @RequestBody Map<String, String> body){
        int noteId = Integer.parseInt(id);
        // getting note
        Note note = noteServiceImpl.findById(noteId);
        if (note != null) {
            String updatedTitle = body.get("title");
            if(updatedTitle != null && !updatedTitle.equals("")) note.setTitle(updatedTitle);
            String updatedContent = body.get("content");
            if(updatedContent != null && !updatedContent.equals("")) note.setContent(updatedContent);
            return noteServiceImpl.save(note);
        } else {
            // Handle the case where the note with the given ID is not found
            throw new IllegalArgumentException("Note not found with ID: " + noteId);
        }
    }

    @DeleteMapping("note/{id}")
    public boolean delete(@PathVariable Integer id){
        noteServiceImpl.deleteById(id);
        return true;
    }
}
