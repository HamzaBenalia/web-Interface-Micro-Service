package com.openclassroom.webInterface.services;
import com.openclassroom.webInterface.dto.NoteDto;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.NoteClient;
import com.openclassroom.webInterface.proxy.PatientClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteClient noteClient;
    @Autowired
    private PatientClient patientClient;

    public void createNote(Note note){
        noteClient.createNote(note);
    }

    public List<NoteDto> getNotes(){
        List<Note> notes = noteClient.getNotes();
        List<String> patientIds = notes.stream().map(note -> note.getPatientId()).collect(Collectors.toList());
        List<Patient> patients = patientClient.getPatientByIds(patientIds);
        List<NoteDto> noteDtos = new ArrayList<>();
        notes.forEach(note -> {
            NoteDto noteDto = new NoteDto();
            noteDto.setId(note.getId());
            noteDto.setDate(note.getDate());
            noteDto.setContent(note.getContent());
            noteDto.setPatientId(note.getPatientId());
            Patient patient = patients.stream().filter(
                 patient1 -> patient1.getId() == Integer.valueOf(note.getPatientId())
            ).findFirst().get();
            noteDto.setPatientLastName(patient.getPrenom());
            noteDto.setPatientFirstName(patient.getNom());
            noteDtos.add(noteDto);
        });
        return noteDtos;
    }

    public void deleteNote(String id){
        noteClient.deleteNote(id);
    }

    public Optional<Note> findById(String id){
        return noteClient.getNote(id);
    }

    public void updateNote(String id , Note note){
        noteClient.updateNote(id,note);
    }

   public void deleteNoteByPatientId(Integer patientId){
        noteClient.deleteNoteByPatientId(patientId);
   }
}
