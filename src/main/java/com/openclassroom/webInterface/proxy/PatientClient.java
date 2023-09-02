package com.openclassroom.webInterface.proxy;

import com.openclassroom.webInterface.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "patients", url = "${patient.url}")
public interface PatientClient {

    @PostMapping("/patient")
    Patient createPatient(@RequestBody Patient patient);

    @GetMapping("/patient")
    List<Patient> getPatients();

    @DeleteMapping("patient/delete/{id}")
    String delete(@PathVariable("id") Integer id);


    @GetMapping("patient/find/{nom}")
    List<Patient> getPatients(@PathVariable("nom") String nom);

    @GetMapping("patient/findByPatientIds")
    List<Patient> getPatientByIds(@RequestParam List<String> patientIds);

    @GetMapping("patient/{id}")
    Optional<Patient> getPatient(@PathVariable("id") Integer id);

    @PutMapping("patient/update/{id}")
    public Patient updatePatient(@PathVariable("id") Integer id, @RequestBody Patient patient);
}
