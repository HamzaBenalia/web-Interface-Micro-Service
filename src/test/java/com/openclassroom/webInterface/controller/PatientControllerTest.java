package com.openclassroom.webInterface.controller;

import com.openclassroom.webInterface.controllers.PatientController;
import com.openclassroom.webInterface.form.PatientForm;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.NoteClient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PatientClient patientClient;

    @MockBean
    private PatientService patientService;

    @MockBean
    private NoteClient noteClient;


    @Test
    public void testViewHomePage() throws Exception {
        mockMvc.perform(get("/showPatientList", "/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/patients/patientHomePage"));
        verify(patientService, times(1)).getPatients();
    }

    @Test
    public void testShowNewPatientForm() throws Exception {
        mockMvc.perform(get("/showPatientRegistration"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/addPatient"));
    }

    @Test
    public void testDeletePatient() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/deletePatient/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showPatientList"));
        verify(patientService, times(1)).deletePatientById(id);
    }

    @Test
    public void testShowFormForPatientUpdateWithExistingId() throws Exception {
        Integer id = 1;
        when(patientService.getPatientById(id)).thenReturn(Optional.of(new Patient()));
        mockMvc.perform(get("/showFormForPatientUpdate/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("/patients/updatePatient"));
    }

    @Test
    public void testUpdatePatientHttpSuccess() throws Exception {
        // Définissez un ID et un formulaire de patient
        Integer mockId = 1;
        PatientForm mockForm = new PatientForm();
        mockForm.setId("1");
        mockForm.setNom("John");
        mockForm.setPrenom("Doe");
        mockForm.setGenre("Homme");
        mockForm.setAdressePostale("Toulouse");
        mockForm.setDateDeNaissance("16/08/1995");
        mockForm.setNumeroDeTelephone("07996633252");
        // ... Initialisez les autres champs selon vos besoins ...

        doNothing().when(patientService).updatePatientById(anyInt(), any(Patient.class));

        mockMvc.perform(post("/updatePatient/" + mockId)
                        .flashAttr("patientForm", mockForm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showPatientList"));

    }

    @Test
    public void testSavePatientHttpSuccess() throws Exception {

        PatientForm mockForm = new PatientForm();
        mockForm.setNom("John");
        mockForm.setPrenom("Doe");
        mockForm.setGenre("Homme");
        mockForm.setAdressePostale("Toulouse");
        mockForm.setNumeroDeTelephone("0766552211");
        mockForm.setDateDeNaissance("18/01/1996");
        doNothing().when(patientService).addPatient(any(Patient.class));
        mockMvc.perform(post("/savePatient")
                        .flashAttr("patientForm", mockForm))  // Utilisez `flashAttr` pour simuler la soumission d'un formulaire
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}

