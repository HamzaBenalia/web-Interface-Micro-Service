package com.openclassroom.webInterface.controller;

import com.openclassroom.webInterface.controllers.NoteController;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.services.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

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

    // ... continue for other methods ...
}
