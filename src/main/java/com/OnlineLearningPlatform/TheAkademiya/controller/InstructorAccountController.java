package com.OnlineLearningPlatform.TheAkademiya.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructor")
@PreAuthorize("hasRole('INSTRUCTOR')")
public class InstructorAccountController {
    @GetMapping("/instructor/dashboard")
    public ResponseEntity<String> getInstructorDashboard() {
        return ResponseEntity.ok("Welcome, Instructor!");

    }
}
