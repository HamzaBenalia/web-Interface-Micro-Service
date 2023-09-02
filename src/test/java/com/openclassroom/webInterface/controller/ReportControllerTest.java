package com.openclassroom.webInterface.controller;
import com.openclassroom.webInterface.controllers.ReportController;
import com.openclassroom.webInterface.model.Patient;
import com.openclassroom.webInterface.model.Report;
import com.openclassroom.webInterface.services.PatientService;
import com.openclassroom.webInterface.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Collections;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
public class ReportControllerTest {


    @Mock
    private ReportService reportService;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private ReportController reportController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void getReportsByName_WithEmptyName_ShouldReturnError() throws Exception {
        mockMvc.perform(get("/reports/search-by-name")
                        .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Veuillez fournir un nom valide."))
                .andExpect(view().name("errorPage"));
    }

    @Test
    public void getReportsByName_WithValidNameAndReportsFound_ShouldReturnReports() throws Exception {
        given(reportService.getReportByPatientName("John"))
                .willReturn(Arrays.asList(new Report())); // A remplacer par un report de test

        mockMvc.perform(get("/reports/search-by-name")
                        .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("reports"))
                .andExpect(model().attribute("hasReport", true))
                .andExpect(view().name("reports/searchForm"));
    }

    @Test
    public void getReportsByName_WithValidNameAndNoReportsFound_ShouldNotReturnReports() throws Exception {
        given(reportService.getReportByPatientName("John"))
                .willReturn(Collections.emptyList());

        mockMvc.perform(get("/reports/search-by-name")
                        .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("hasReport", false))
                .andExpect(view().name("reports/searchForm"));
    }

    @Test
    public void searchReportsForm_ShouldReturnForm() throws Exception {
        given(patientService.getPatients())
                .willReturn(Arrays.asList(new Patient())); // A remplacer par un patient de test

        mockMvc.perform(get("/reports/search"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patients"))
                .andExpect(model().attribute("hasReport", false))
                .andExpect(model().attribute("hasSearch", false))
                .andExpect(view().name("reports/searchForm"));
    }
}
