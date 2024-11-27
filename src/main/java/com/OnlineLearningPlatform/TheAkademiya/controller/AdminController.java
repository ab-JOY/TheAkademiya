package com.OnlineLearningPlatform.TheAkademiya.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/admin/adminDashboard")
    public ResponseEntity<String> getAdminDashboard(){
        return ResponseEntity.ok("Welcome, Admin!");
    }
}
