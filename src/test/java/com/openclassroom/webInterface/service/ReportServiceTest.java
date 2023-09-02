package com.openclassroom.webInterface.service;

import com.openclassroom.webInterface.model.Report;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.proxy.PatientClient;
import com.openclassroom.webInterface.proxy.ReportClient;
import com.openclassroom.webInterface.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {


    @Mock
    private ReportClient reportClient;

    @Mock
    private PatientClient patientClient;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReport() {
        Patient patient = new Patient(1,"benalia","hamza","16/05/1998","Homme","Toulosue","0755226633");
        String patientId = "123";
        Report mockReport = new Report();
        when(reportClient.getReport(patientId)).thenReturn(mockReport);

        Report report = reportService.getReport(patientId);

        assertEquals(mockReport, report);
        verify(reportClient, times(1)).getReport(patientId);
    }


    @Test
    public void testGetAllReports() {
        Patient patient = new Patient(1,"benalia","hamza","16/02/1885","Homme","","0788552266");

        when(patientClient.getPatients()).thenReturn(Arrays.asList(patient));
        when(reportClient.getReport(anyString())).thenReturn(new Report());

        List<Report> reports = reportService.getAllReports();

        assertEquals(Arrays.asList(patient).size(), reports.size());
        verify(reportClient, times(Arrays.asList(patient).size())).getReport(anyString());
    }

    @Test
    public void testGetReportByPatientName() {
        String name = "John";
        Patient patient = new Patient(1,name,"hamza","16/02/1885","Homme","","0788552266");
        when(patientClient.getPatients(name)).thenReturn(Arrays.asList(patient));
        when(reportClient.getReport(anyString())).thenReturn(new Report());

        List<Report> reports = reportService.getReportByPatientName(name);

        assertEquals(Arrays.asList(patient).size(), reports.size());
        verify(reportClient, times(Arrays.asList(patient).size())).getReport(anyString());
    }
}
