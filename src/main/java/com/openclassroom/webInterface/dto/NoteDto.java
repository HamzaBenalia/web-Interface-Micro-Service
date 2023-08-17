package com.openclassroom.webInterface.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private String id;

    private LocalDate date;

    private String patientId;

    private String patientFirstName;

    private String patientLastName;

    private String content;
}
