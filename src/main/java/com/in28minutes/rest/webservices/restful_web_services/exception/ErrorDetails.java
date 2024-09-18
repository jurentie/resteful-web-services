package com.in28minutes.rest.webservices.restful_web_services.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    
    // timestamp
    private LocalDateTime timestamp;
    // message
    private String message;
    // details
    private String details;

}
