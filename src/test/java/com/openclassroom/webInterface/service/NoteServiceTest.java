package com.openclassroom.webInterface.service;
import com.openclassroom.webInterface.dto.NoteDto;
import com.openclassroom.webInterface.form.NoteForm;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.NoteClient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.services.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceTest {


    @Mock
    private NoteClient noteClient;

    @Mock
    private PatientClient patientClient;

    @InjectMocks
    private NoteService noteService;  // Remplacez par le nom réel de votre service si ce n'est pas "NoteService".

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNote() {
        Note note = new Note();
        noteService.createNote(note);

        verify(noteClient, times(1)).createNote(note);
    }


    @Test
    public void testGetNotes() {
        Note note = new Note("1", LocalDate.now(),"1","content");
        Patient patient = new Patient(1,"benalia","hamza","16/05/1998","Homme","Toulouse","0766752255");
        List<Note> mockedNotes = Arrays.asList(note);
        List<Patient> mockedPatients = Arrays.asList(patient);

        when(noteClient.getNotes()).thenReturn(mockedNotes);
        when(patientClient.getPatientByIds(anyList())).thenReturn(mockedPatients);

        List<NoteDto> result = noteService.getNotes();

        assertNotNull(result);
        assertEquals(mockedNotes.size(), result.size());
    }


    @Test
    public void testDeleteNote() {
        String id = "123";
        noteService.deleteNote(id);

        verify(noteClient, times(1)).deleteNote(id);
    }

    @Test
    public void testFindById() {
        String id = "123";
        when(noteClient.getNote(id)).thenReturn(Optional.of(new Note()));

        Optional<Note> returnedNote = noteService.findById(id);

        verify(noteClient, times(1)).getNote(id);
        assertTrue(returnedNote.isPresent());
    }

    @Test
    public void testUpdateNote() {
        String id = "123";
        Note note = new Note();
        noteService.updateNote(id, note);

        verify(noteClient, times(1)).updateNote(id, note);
    }

}
