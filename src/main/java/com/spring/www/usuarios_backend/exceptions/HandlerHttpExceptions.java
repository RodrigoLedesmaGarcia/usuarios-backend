package com.spring.www.usuarios_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class HandlerHttpExceptions {

    public static ResponseEntity<Map<String, Object>> errorResponse (HttpStatus status, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status.value());
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<Map<String, Object>> notFound (String message){
        return errorResponse(HttpStatus.NOT_FOUND, message);
    }

    public static ResponseEntity<Map<String, Object>> badRequest  (String message){
        return errorResponse(HttpStatus.BAD_REQUEST, message);
    }
}
