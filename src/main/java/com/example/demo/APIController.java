package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @GetMapping("/normal")
    public String normal() {
        logger.info("Normal request received");
        return "Normal Response";
    }

    @GetMapping("/nullPointer")
    public String nullPointer() {
        try {
            String npe = null;
            npe.toString();
        } catch (NullPointerException e) {
            logger.error("Null Pointer Exception occurred", e);
        }
        return "Null Pointer Exception Occurred";
    }

    @GetMapping("/generalError")
    public String generalError() {
        try {
            throw new IllegalArgumentException("General error occurred");
        } catch (IllegalArgumentException e) {
            logger.error("General error occurred", e);
        }
        return "General Error Occurred";
    }

    @GetMapping("/notFound")
    public String notFound() {
        try {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Error Occurred");
        } catch (ResponseStatusException e) {
            logger.error("Not Found Error Occurred", e);
        }
        return "Not Found Error Occurred";
    }
}
