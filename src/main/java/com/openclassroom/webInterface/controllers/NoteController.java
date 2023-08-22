package com.openclassroom.webInterface.controllers;
import com.openclassroom.webInterface.form.NoteForm;
import com.openclassroom.webInterface.form.PatientForm;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.services.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Operation(
            description = "Show a registration form for a note"
    )
    @GetMapping("/showNoteRegistration")
    public String showNewPatientForm(Model model) {
        // create model attribute to bind form data
        NoteForm noteForm = new NoteForm();
        model.addAttribute("noteForm", noteForm);
        return "notes/addNote";
    }

    @Operation(
            description = "Show the list of notes"
    )

    @GetMapping("/showNoteList")
    public String viewNoteHomePage(Model model) {
        model.addAttribute("listNote", noteService.getNotes());
        return "notes/noteHomePage";
    }

    @Operation(
            description = "save a note "
    )

    @PostMapping("/saveNote")
    public String saveNote(@Valid @ModelAttribute("noteForm") NoteForm noteForm,
                              BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "notes/addNote";
        }

        Note note = new Note();
        note.setPatientId(noteForm.getPatientId());
        LocalDate date = LocalDate.now();
        note.setDate(date);
        note.setContent(noteForm.getContent());
        noteService.createNote(note);
        return "redirect:/showNoteList";
    }


    @Operation(
            description = "Show a form to update a note information"
    )
    @GetMapping("/showFormForNoteUpdate/{id}")
    public String showFormForBidListUpdate(@PathVariable(value = "id") String id, Model model) {
        try {
            Optional<Note> noteOpt = noteService.findById(id);
            if (noteOpt.isPresent()) {
                Note note = noteOpt.get();
                model.addAttribute("noteForm", note);
                return "notes/updateNote";
            } else {
                return "redirect:/notes/noteHomePage";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "redirect:/bidListHomePage";
        }
    }

    @Operation(
            description = "update a note information"
    )

    @PostMapping("/updateNote/{id}")
    public String updateNote(@PathVariable(value = "id") String id, @Valid @ModelAttribute("noteForm") NoteForm noteForm, BindingResult result) {
        if (result.hasErrors()) {
            return "notes/updateNote";
        }
        try {
            Note updatedNote = new Note();
            updatedNote.setPatientId(noteForm.getPatientId());
            LocalDate now = LocalDate.now();
            updatedNote.setDate(now);
            updatedNote.setPatientId(noteForm.getPatientId());
            updatedNote.setContent(noteForm.getContent());
            noteService.updateNote(id,updatedNote);
            return "redirect:/showNoteList";
        } catch (Exception exception) {
            result.rejectValue("", "", "error : " + exception.getMessage());
            return "notes/updateNote";
        }
    }

    @Operation(
            description = "Delete a note via it's id"
    )

    @GetMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable(value = "id") String id) {

        // call delete employee method
        this.noteService.deleteNote(id);
        return "redirect:/showNoteList";
    }
}
