package com.openclassroom.webInterface.form;
import com.openclassroom.webInterface.enums.Risk;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Integer age;
    @NotNull
    private Risk risk;
    @NotEmpty
    private List<String> triggers;
}
