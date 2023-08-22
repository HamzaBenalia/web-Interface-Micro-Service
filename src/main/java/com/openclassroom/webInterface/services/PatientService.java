package com.openclassroom.webInterface.services;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.PatientClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientClient patientClient;


    /**
     * This method is for creating a patient on the interface
     * @param patient
     */
    public void addPatient(Patient patient) {
        patientClient.createPatient(patient);
    }

    /**
     * This method is for getting a list of patients on the interface
     * @return
     */
    public List<Patient> getPatients() {
        return patientClient.getPatients();
    }

    /**
     * This method if for deleting a patient on the interface
     * @param id
     */
    public void deletePatientById(Integer id){
        patientClient.delete(id);
    }

    /**
     * This method is for getting a patient by id on the interface
     * @param id
     * @return
     */
    public Optional<Patient> getPatientById(Integer id){
        return patientClient.getPatient(id);
    }

    /**
     * This method is for updating a patient via the id on the interface
     * @param id
     * @param patient
     */
    public void updatePatientById(Integer id, Patient patient){
        patientClient.updatePatient(id,patient);
    }
}
