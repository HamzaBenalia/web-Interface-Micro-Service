package com.openclassroom.webInterface.proxy;

import com.openclassroom.webInterface.model.Note;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "note", url = "${note.url}")

public interface NoteClient {


    @PostMapping("/note")
    Note createNote(@RequestBody Note note);

    @GetMapping("/note")
    List<Note> getNotes();

    @DeleteMapping("/note/{id}")
    String deleteNote(@PathVariable String id);

    @GetMapping("/note/{id}")
    Optional<Note> getNote(@PathVariable String id);

    @PutMapping("/note/update/{id}")
    Note updateNote(@PathVariable("id") String id, @RequestBody Note note);

    @DeleteMapping("note/patient/{patientId}")
    void deleteNoteByPatientId(@PathVariable Integer patientId);
}
