package com.openclassroom.webInterface.controllers;
import com.medic.reports.model.Report;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.services.PatientService;
import com.openclassroom.webInterface.services.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class ReportController {

    private final ReportService reportService;
    private final PatientService patientService;
    private boolean hasSearch;
    private List<Patient> patients;

    @Autowired
    public ReportController(ReportService reportService, PatientService patientService) {
        this.reportService = reportService;
        this.patientService = patientService;
    }

    @GetMapping("/reports/search-by-name")
    public String getReportsByName(@RequestParam String name, Model model) {
        this.hasSearch = true;
        if (name == null || name.trim().isEmpty()) {
            log.warn("Nom invalide ou manquant");
            model.addAttribute("message", "Veuillez fournir un nom valide.");
            return "errorPage"; // Supposons que vous ayez une page d'erreur générique
        }

        List<Report> reports = reportService.getReportByPatientName(name);
        boolean hasReport = !reports.isEmpty();
        model.addAttribute("hasReport", hasReport);
        model.addAttribute("reports", reports);
        model.addAttribute("hasSearch", this.hasSearch);
        model.addAttribute("patients", patients);

        return "reports/searchForm";
    }


    @GetMapping("/reports/search")
    public String searchReportsForm(Model model) {
        this.hasSearch = false;
        setPatients(patientService.getPatients());
        model.addAttribute("patients", patients);
        model.addAttribute("hasSearch", this.hasSearch);
        model.addAttribute("hasReport", false);
        return "reports/searchForm";
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}

