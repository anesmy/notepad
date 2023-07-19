package com.nesmy.notepad.web.controller;

import com.nesmy.notepad.exceptions.NoteNotFoundException;
import com.nesmy.notepad.models.Note;
import com.nesmy.notepad.web.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class NoteController {

    private NoteService noteServiceImpl;
    @GetMapping("/")
    public List<Note> showAll(){
        List<Note> notes = noteServiceImpl.findAll();
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No notes found");
        }
        return notes;
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<Note> show(@PathVariable Integer id){
        Note note = noteServiceImpl.findById(id);
        if (note == null) {
            throw new NoteNotFoundException("Note not found with ID: " + id);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/note/search")
    public  ResponseEntity<List<Note>> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        List<Note> searchResults = noteServiceImpl.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        if (searchResults.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PostMapping("/note")
    public ResponseEntity<Note> create(@RequestBody Map<String, String> body){
        try {
            String title = body.get("title");
            String content = body.get("content");
            Note newNote = noteServiceImpl.save(new Note(title, content));
            return ResponseEntity.ok(newNote);
        } catch (Exception e) {
            log.error("Failed to create Note", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<Note> update(@PathVariable String id, @RequestBody Map<String, String> body){
        int noteId = Integer.parseInt(id);
        Note note = noteServiceImpl.findById(noteId);

        if (note != null) {
            String updatedTitle = body.get("title");
            if(updatedTitle != null && !updatedTitle.equals("")) note.setTitle(updatedTitle);
            String updatedContent = body.get("content");
            if(updatedContent != null && !updatedContent.equals("")) note.setContent(updatedContent);
            return ResponseEntity.ok(noteServiceImpl.save(note));
        } else {
            throw new NoteNotFoundException("Note not found with ID: " + id);
        }
    }

    @DeleteMapping("note/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean isDeleted = noteServiceImpl.deleteById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
        } else {
            throw new NoteNotFoundException("Note not found with ID: " + id);
        }
    }
}
