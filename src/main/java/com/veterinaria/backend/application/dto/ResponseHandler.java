package com.veterinaria.backend.application.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        // LinkedHashMap mantiene el orden de inserción de las propiedades
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("status", status.value());

        // Corrección para no depender de librerías externas en el chequeo de texto
        if (message != null && !message.trim().isEmpty()) {
            response.put("message", message);
        }

        if (responseObj != null) {
            response.put("data", responseObj);
        }

        return new ResponseEntity<>(response, status);
    }

    // --- Tus métodos semánticos (¡Están impecables!) ---

    public static ResponseEntity<Object> success(Object responseObj, String message) {
        return generateResponse(message, HttpStatus.OK, responseObj);
    }

    public static ResponseEntity<Object> success(Object responseObj) {
        return generateResponse(null, HttpStatus.OK, responseObj);
    }

    public static ResponseEntity<Object> created(Object responseObj) {
        return generateResponse(null, HttpStatus.CREATED, responseObj);
    }

    public static ResponseEntity<Object> noContent() {
        return generateResponse(null, HttpStatus.NO_CONTENT, null);
    }

    public static ResponseEntity<Object> badRequest(String message) {
        return generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    public static ResponseEntity<Object> unauthorized(String message) {
        return generateResponse(message, HttpStatus.UNAUTHORIZED, null);
    }

    public static ResponseEntity<Object> forbidden(String message) {
        return generateResponse(message, HttpStatus.FORBIDDEN, null);
    }

    public static ResponseEntity<Object> notFound(String message) {
        return generateResponse(message, HttpStatus.NOT_FOUND, null);
    }

    public static ResponseEntity<Object> internalError() {
        return generateResponse("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}