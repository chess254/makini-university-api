package com.makiniuniversity.dto;

import lombok.Data;

@Data
public class StudentValidationResponseDTO {

    private boolean valid;
    private String message;
}