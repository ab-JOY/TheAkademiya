package com.OnlineLearningPlatform.TheAkademiya.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentAccountController {
    @GetMapping("/student/dashboard")
    public ResponseEntity<String> getStudentDashboard() {
        return ResponseEntity.ok("Welcome, Instructor!");

    }
}
