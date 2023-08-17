package com.openclassroom.webInterface.service;

import com.medic.reports.model.Report;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.proxy.ReportClient;
import com.openclassroom.webInterface.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReportServiceTest {


    @Mock
    private ReportClient reportClient;

    @Mock
    private PatientClient patientClient;

    @InjectMocks
    private ReportService reportService;  // Supposons que le nom de votre service soit "ReportService".

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReport() {
        String patientId = "123";
        Report mockReport = new Report();
        when(reportClient.getReport(patientId)).thenReturn(mockReport);

        Report report = reportService.getReport(patientId);

        assertEquals(mockReport, report);
        verify(reportClient, times(1)).getReport(patientId);
    }


    @Test
    public void testGetAllReports() {
        List<Patient> mockPatients = Arrays.asList(new Patient(), new Patient());
        when(patientClient.getPatients()).thenReturn(mockPatients);
        when(reportClient.getReport(anyString())).thenReturn(new Report());  // retourne un report mocké pour n'importe quelle ID

        List<Report> reports = reportService.getAllReports();

        assertEquals(mockPatients.size(), reports.size());
        verify(reportClient, times(mockPatients.size())).getReport(anyString());
    }

    @Test
    public void testGetReportByPatientName() {
        String name = "John";
        List<Patient> mockPatients = Arrays.asList(new Patient(), new Patient());
        when(patientClient.getPatients(name)).thenReturn(mockPatients);
        when(reportClient.getReport(anyString())).thenReturn(new Report());

        List<Report> reports = reportService.getReportByPatientName(name);

        assertEquals(mockPatients.size(), reports.size());
        verify(reportClient, times(mockPatients.size())).getReport(anyString());
    }
}
