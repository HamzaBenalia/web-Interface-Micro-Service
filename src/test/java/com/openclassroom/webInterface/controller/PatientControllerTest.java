package com.openclassroom.webInterface.controller;

import com.openclassroom.webInterface.controllers.PatientController;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.services.PatientService;
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

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
    }
//
//    @Test
//    public void testViewHomePage() throws Exception {
//        mockMvc.perform(get("/showPatientList"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/patients/patientHomePage"));
//        verify(patientService, times(1)).getPatients();
//    }
//
//    @Test
//    public void testShowNewPatientForm() throws Exception {
//        mockMvc.perform(get("/showPatientRegistration"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("patients/addPatient"));
//    }
//
//    @Test
//    public void testDeletePatient() throws Exception {
//        Integer id = 1;
//        mockMvc.perform(get("/deletePatient/" + id))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/showPatientList"));
//        verify(patientService, times(1)).deletePatientById(id);
//    }
//
//    @Test
//    public void testShowFormForPatientUpdateWithExistingId() throws Exception {
//        Integer id = 1;
//        when(patientService.getPatientById(id)).thenReturn(Optional.of(new Patient()));
//        mockMvc.perform(get("/showFormForPatientUpdate/" + id))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/patients/updatePatient"));
//    }

    // ... continue for other methods ...
}

