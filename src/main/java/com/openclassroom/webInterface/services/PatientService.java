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


    public void addPatient(Patient patient) {
        patientClient.createPatient(patient);
    }

    public List<Patient> getPatients() {
        return patientClient.getPatients();
    }

    public void deletePatientById(Integer id){
        patientClient.delete(id);
    }

    public Optional<Patient> getPatientById(Integer id){
        return patientClient.getPatient(id);
    }

    public void updatePatientById(Integer id, Patient patient){
        patientClient.updatePatient(id,patient);
    }
}
