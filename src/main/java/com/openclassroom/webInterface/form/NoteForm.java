package com.openclassroom.webInterface.form;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteForm {

    private String id;
    @NotBlank(message = "le patient id est obligatoire")
    private String patientId;
    @NotBlank (message = "le content est obligatoire")
    private String content;
}
