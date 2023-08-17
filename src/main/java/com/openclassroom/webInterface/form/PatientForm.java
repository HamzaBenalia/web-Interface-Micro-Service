package com.openclassroom.webInterface.form;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientForm {


    private String id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String dateDeNaissance;
    @NotBlank
    private String genre;
    @NotBlank
    private String adressePostale;
    @NotBlank
    private String numeroDeTelephone;
}
