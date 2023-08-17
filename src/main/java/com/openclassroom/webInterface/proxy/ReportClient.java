package com.openclassroom.webInterface.proxy;

import com.medic.reports.model.Report;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "report", url = "http://localhost:9091")
public interface ReportClient {

    @GetMapping("/report/{patientId}")
    Report getReport(@PathVariable String patientId);
}
