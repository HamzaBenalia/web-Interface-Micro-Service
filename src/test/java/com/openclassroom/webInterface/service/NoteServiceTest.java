package com.openclassroom.webInterface.service;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.proxy.NoteClient;
import com.openclassroom.webInterface.services.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceTest {


    @Mock
    private NoteClient noteClient;

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
        when(noteClient.getNotes()).thenReturn(Arrays.asList(new Note(), new Note()));

        List<Note> notes = noteService.getNotes();

        verify(noteClient, times(1)).getNotes();
        // Vous pourriez également vouloir vérifier la taille de la liste retournée ou d'autres propriétés
        // assertEquals(2, notes.size());
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
