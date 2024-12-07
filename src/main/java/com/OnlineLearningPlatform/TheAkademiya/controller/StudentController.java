package com.OnlineLearningPlatform.TheAkademiya.controller;

import com.OnlineLearningPlatform.TheAkademiya.dto.UserLoginDto;
import com.OnlineLearningPlatform.TheAkademiya.dto.UserRegistrationDto;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import com.OnlineLearningPlatform.TheAkademiya.repository.StudentRepository;
import com.OnlineLearningPlatform.TheAkademiya.service.ArchiveService;
import com.OnlineLearningPlatform.TheAkademiya.service.StudentService;
import com.OnlineLearningPlatform.TheAkademiya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    ArchiveService archiveService;

    @PostMapping("/student/newStudent")
    Student newStudent(@RequestBody Student newStudent){
        return studentService.newStudent(newStudent);
    }

    @GetMapping("/student/allStudents")
    List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{studentId}")
    Student getStudentById(@PathVariable Long studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new RuntimeException("Student does not exist"));
    }

    @PutMapping("/student/{studentId}")
    public Student updateStudent(@RequestBody Student newStudent, @PathVariable Long studentId){
        return  studentService.updateStudent(studentId, newStudent);
    }

    @PutMapping("/student/studentArchive/{studentId}")
    public ResponseEntity<?> archiveStudent(@PathVariable Long studentId){
        archiveService.StudentArchive(studentId);
        return ResponseEntity.ok("Success");
    }

    @Autowired
    private UserService userService;


    @PostMapping("/student/register")
    public ResponseEntity<String> registerStudent(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.registerUser(registrationDto);
            return ResponseEntity.ok("Student registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/student/login")
    public ResponseEntity<String> loginStudent(@RequestBody UserLoginDto loginDto) {
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
