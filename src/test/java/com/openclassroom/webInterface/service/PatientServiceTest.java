package com.openclassroom.webInterface.service;

import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.services.PatientService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientServiceTest {


    @Mock
    private PatientClient patientClient;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPatient() {
        Patient patient = new Patient(); // Vous pouvez initialiser avec des données si nécessaire
        patientService.addPatient(patient);

        verify(patientClient, times(1)).createPatient(patient);
    }


    @Test
    public void testGetPatients() {
        // Mocking the behavior of patientClient.getPatients() to return a list of patients
        when(patientClient.getPatients()).thenReturn(Arrays.asList(new Patient(), new Patient()));

        List<Patient> patients = patientService.getPatients();

        // Verify that patientClient.getPatients() was called once
        verify(patientClient, times(1)).getPatients();
        // Optional: Assert that the returned list has the expected size or contents
        assertEquals(2, patients.size());
    }

    @Test
    public void testDeletePatientById() {
        int id = 1;
        patientService.deletePatientById(id);

        // Verify that patientClient.delete(id) was called once with the provided id
        verify(patientClient, times(1)).delete(id);
    }

    @Test
    public void testGetPatientById() {
        int id = 1;
        Patient mockPatient = new Patient();
        // Mocking the behavior of patientClient.getPatient(id) to return a patient
        when(patientClient.getPatient(id)).thenReturn(Optional.of(mockPatient));

        Optional<Patient> returnedPatient = patientService.getPatientById(id);

        // Verify that patientClient.getPatient(id) was called once with the provided id
        verify(patientClient, times(1)).getPatient(id);
        // Optional: Assert that the returned patient is the expected one
        assertTrue(returnedPatient.isPresent());
        assertEquals(mockPatient, returnedPatient.get());
    }

    @Test
    public void testUpdatePatientById() {
        int id = 1;
        Patient patient = new Patient();
        patientService.updatePatientById(id, patient);

        // Verify that patientClient.updatePatient(id, patient) was called once with the provided id and patient
        verify(patientClient, times(1)).updatePatient(id, patient);
    }
}





