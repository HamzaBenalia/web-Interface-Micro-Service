package com.openclassroom.webInterface.proxy;

import com.openclassroom.webInterface.model.Report;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "report", url = "${report.url}")
public interface ReportClient {

    @GetMapping("/report/{patientId}")
    Report getReport(@PathVariable String patientId);
}
