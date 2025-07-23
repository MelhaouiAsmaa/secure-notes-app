package com.example.securenotes.controller;

import com.example.securenotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired private NoteService noteService;

    @PostMapping
    public String createNote(@RequestBody String content) {
        return noteService.createNote(content).getId();
    }

    @GetMapping("/{id}")
    public String getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
    }
}

