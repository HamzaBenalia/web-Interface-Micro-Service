package com.openclassroom.webInterface.form;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteForm {
    @NotBlank
    private String id;
    @NotBlank
    private String patientId;
    @NotBlank
    private String content;
}
