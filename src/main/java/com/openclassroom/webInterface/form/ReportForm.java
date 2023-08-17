package com.openclassroom.webInterface.form;
import com.medic.reports.enums.Risk;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportForm {

    @NotBlank
    private String patientId;
    @NotBlank

    private String firstName;
    @NotBlank

    private String lastName;
    @NotBlank

    private String gender;
    @NotBlank

    private String birthdate;
    @NotBlank

    private Integer age;
    @NotBlank

    private Risk risk;
    @NotBlank

    private List<String> triggers;
}
