package com.openclassroom.webInterface.controller;

import com.openclassroom.webInterface.controllers.NoteController;
import com.openclassroom.webInterface.form.NoteForm;
import com.openclassroom.webInterface.form.PatientForm;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.NoteClient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.services.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private PatientClient patientClient;

    @MockBean
    private NoteClient noteClient;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testShowNewNoteForm() throws Exception {
        mockMvc.perform(get("/showNoteRegistration"))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/addNote"));
    }

    @Test
    public void testViewNoteHomePage() throws Exception {
        mockMvc.perform(get("/showNoteList"))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/noteHomePage"));
        verify(noteService, times(1)).getNotes();
    }

    @Test
    public void testDeleteNote() throws Exception {
        String id = "123";
        mockMvc.perform(get("/deleteNote/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showNoteList"));
        verify(noteService, times(1)).deleteNote(id);
    }

    @Test
    public void testShowFormForNoteUpdateWithExistingId() throws Exception {
        String id = "123";
        when(noteService.findById(id)).thenReturn(Optional.of(new Note()));
        mockMvc.perform(get("/showFormForNoteUpdate/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/updateNote"));
    }

    @Test
    public void testUpdateNoteHttpSuccess() throws Exception {

        String mockId = "1";
        NoteForm mockForm = new NoteForm();
        mockForm.setId("1");
        mockForm.setPatientId("2");
        mockForm.setContent("content");

        doNothing().when(noteService).updateNote(anyString(), any(Note.class));

        mockMvc.perform(post("/updateNote/" + mockId)
                        .flashAttr("noteForm", mockForm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showNoteList"));
    }

    @Test
    public void testSaveNoteHttpSuccess() throws Exception {

        NoteForm mockForm = new NoteForm();
        mockForm.setId("1");
        mockForm.setPatientId("2");
        mockForm.setContent("content");

        doNothing().when(noteService).createNote(any(Note.class));
        mockMvc.perform(post("/saveNote")
                        .flashAttr("noteForm", mockForm))  // Utilisez `flashAttr` pour simuler la soumission d'un formulaire
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showNoteList"));
    }
}
