package com.openclassroom.webInterface.services;

import com.medic.reports.model.Report;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.proxy.ReportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportClient reportClient;

    @Autowired
    private PatientClient patientClient;

    public Report getReport(String patientId){
        return reportClient.getReport(patientId);
    }


    public List<Report> getAllReports() {
        List<Patient> patients = patientClient.getPatients();
        List<Report> reports = new ArrayList<>();

        for (Patient patient : patients) {
            reports.add(reportClient.getReport(patient.getId().toString()));  // transformez l'ID en String si nécessaire
        }

        return reports;
    }

    public List<Report> getReportByPatientName(String name){
        List<Patient> patients = patientClient.getPatients(name);

        List<Report> reports = new ArrayList<>();
        for(Patient patient1: patients){
            reports.add(reportClient.getReport(patient1.getId().toString()));
        }
        return reports;
    }

}

