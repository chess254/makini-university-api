package com.makiniuniversity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentValidationRequestDTO {

    @NotBlank(message = "Student number is required")
    private String studentNumber;
}