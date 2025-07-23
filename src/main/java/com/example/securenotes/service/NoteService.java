package com.example.securenotes.service;

import com.example.securenotes.model.Note;
import com.example.securenotes.repository.DynamoDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoteService {
    @Autowired private KmsService kmsService;
    @Autowired private DynamoDbRepository repository;

    public Note createNote(String plainText) {
        String encrypted = kmsService.encrypt(plainText);
        Note note = new Note(UUID.randomUUID().toString(), encrypted);
        repository.save(note);
        return note;
    }

    public String getNote(String id) {
        Note note = repository.get(id);
        return kmsService.decrypt(note.getContent());
    }

    public void deleteNote(String id) {
        repository.delete(id);
    }
}

