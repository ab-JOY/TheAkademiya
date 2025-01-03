package com.OnlineLearningPlatform.TheAkademiya.controller;

import com.OnlineLearningPlatform.TheAkademiya.dto.UserLoginDto;
import com.OnlineLearningPlatform.TheAkademiya.dto.UserRegistrationDto;
import com.OnlineLearningPlatform.TheAkademiya.model.Instructor;
import com.OnlineLearningPlatform.TheAkademiya.repository.InstructorRepository;
import com.OnlineLearningPlatform.TheAkademiya.service.ArchiveService;
import com.OnlineLearningPlatform.TheAkademiya.service.InstructorService;
import com.OnlineLearningPlatform.TheAkademiya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/instructor/newInstructor")
    Instructor newInstructor(@RequestBody Instructor newInstructor){
        return instructorService.newInstructor(newInstructor);
    }

    @GetMapping("/instructor/allInstructor")
    List<Instructor> getAllInstructor(){
        return instructorRepository.findAll();
    }

    @GetMapping("/instructor/{instructorId}")
    Instructor getInstructorById(@PathVariable Long instructorId){
        return instructorRepository.findById(instructorId)
                .orElseThrow(()-> new RuntimeException("Instructor does not exist"));
    }

    @PutMapping("/instructor/{instructorId}")
    public Instructor updateInstructor(@RequestBody Instructor newInstructor, @PathVariable Long instructorId){
        return instructorService.updateInstructor(instructorId, newInstructor);
    }

    @PutMapping("/instructor/instructorArchive/{instructorId}")
    public ResponseEntity<?> archiveInstructor(@PathVariable Long instructorId){
        archiveService.InstructorArchive(instructorId);
        return ResponseEntity.ok("Success");
    }

    @Autowired
    private UserService userService;

    @PostMapping("/instructor/register")
    public ResponseEntity<String> registerInstructor(@RequestBody UserRegistrationDto userRegistrationDto){
        try {
            userService.registerUser(userRegistrationDto);
            return ResponseEntity.ok("Instructor registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/instructor/login")
    public ResponseEntity<String> loginInstructor(@RequestBody UserLoginDto loginDto) {
        try {
            boolean isAuthenticated = userService.authenticateUser(loginDto);
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
