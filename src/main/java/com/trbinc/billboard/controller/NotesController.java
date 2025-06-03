package com.trbinc.billboard.controller;

import com.trbinc.billboard.models.Note;
import com.trbinc.billboard.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {

    @Autowired
    private NotesRepository noteRepo;

    @CrossOrigin(origins = "*")
    @GetMapping("/notes")
    public ResponseEntity<?> getAllNotes() {
        List<Note> notes = noteRepo.findAll();
        System.out.println(notes);
        if (notes.size() > 0) {
            return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No notes available", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/note/create")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        if (note.getQuote() == null) return new ResponseEntity<>("Quote should not be empty", HttpStatus.resolve(400));
        try {
            if (note.getQuote().isEmpty()) return new ResponseEntity<>("Quote should not be empty", HttpStatus.resolve(400));
            note.setCreation(new Date(System.currentTimeMillis()));
            note.setUpdation(new Date(System.currentTimeMillis()));
            noteRepo.save(note);

            return new ResponseEntity<Note>(note, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/note/delete")
    public ResponseEntity<?> deleteNote(@RequestParam String id) {
        try {
            Optional<Note> fetched = noteRepo.findById(id);
            if (fetched.isEmpty()) return new ResponseEntity<>("Note not found", HttpStatus.resolve(400));
            Note note = fetched.get();
            noteRepo.delete(note);

            return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
