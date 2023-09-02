package com.openclassroom.webInterface.controllers;
import com.openclassroom.webInterface.form.PatientForm;
import com.openclassroom.webInterface.model.Note;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.services.NoteService;
import com.openclassroom.webInterface.services.PatientService;
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

import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private NoteService noteService;


    @Operation(
            description = "Show a form to registrate a patient"
    )
    @GetMapping("/showPatientRegistration")
    public String showNewPatientForm(Model model) {
        // create model attribute to bind form data
        PatientForm patientForm = new PatientForm();
        model.addAttribute("patientForm", patientForm);
        return "patients/addPatient";
    }

    @Operation(
            description = "Show the list of registered patients"
    )

    @GetMapping({"/showPatientList","/"})
    public String viewHomePage(Model model) {
        model.addAttribute("listPatient", patientService.getPatients());
        return "patients/patientHomePage";
    }

    @Operation(
            description = "Save a patient"
    )
    @PostMapping("/savePatient")
    public String savePatient(@Valid @ModelAttribute("patientForm") PatientForm patientForm,
                              BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "patients/addPatient";
        }

            Patient patient = new Patient();
            patient.setNom(patientForm.getNom());
            patient.setPrenom(patientForm.getPrenom());
            patient.setGenre(patientForm.getGenre());
            patient.setAdressePostale(patientForm.getAdressePostale());
            patient.setDateDeNaissance(patientForm.getDateDeNaissance());
            patient.setNumeroDeTelephone(patientForm.getNumeroDeTelephone());
            patientService.addPatient(patient);
            return "redirect:/";
        }

    @Operation(
            description = "Show the form for updating a patient"
    )

    @GetMapping("/showFormForPatientUpdate/{id}")
    public String showFormForPatientUpdate(@PathVariable(value = "id") Integer id, Model model) {
        try {
            Optional<Patient> patientOpt = patientService.getPatientById(id);
            if (patientOpt.isPresent()) {
                Patient patient = patientOpt.get();
                model.addAttribute("patientForm", patient);
                return "patients/updatePatient";
            } else {
                return "redirect:/patients/patientHomePage";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "redirect:/patients/patientHomePage";
        }
    }

    @Operation(
            description = "Update a patient"
    )

    @PostMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable(value = "id") Integer id, @Valid @ModelAttribute("patientForm") PatientForm patientForm, BindingResult result) {
        if (result.hasErrors()) {
            return "patients/updatePatient";
        }
        try {
            Patient updatedPatient = new Patient();
            updatedPatient.setId(Integer.valueOf(patientForm.getId()));
            updatedPatient.setNom(patientForm.getNom());
            updatedPatient.setPrenom(patientForm.getPrenom());
            updatedPatient.setGenre(patientForm.getGenre());
            updatedPatient.setAdressePostale(patientForm.getAdressePostale());
            updatedPatient.setDateDeNaissance(patientForm.getDateDeNaissance());
            updatedPatient.setNumeroDeTelephone(patientForm.getNumeroDeTelephone());

            patientService.updatePatientById(id, updatedPatient);
            return "redirect:/showPatientList";
        } catch (Exception exception) {
            result.rejectValue("", "", "error : " + exception.getMessage());
            return "updatePatient";
        }
    }

    @Operation(
            description = "Delete a patient via it's id"
    )

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable(value = "id") Integer id) {

        // call delete employee method
        this.patientService.deletePatientById(id);
        this.noteService.deleteNoteByPatientId(id);
        return "redirect:/showPatientList";
    }
}

