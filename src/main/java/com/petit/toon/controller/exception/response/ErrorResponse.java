package com.petit.toon.controller.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> validation = new HashMap<>();

    @Builder
    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String fieldName, String errorMessage) {
        validation.put(fieldName, errorMessage);
    }
}
