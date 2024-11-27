package com.OnlineLearningPlatform.TheAkademiya.controller;

import com.OnlineLearningPlatform.TheAkademiya.dto.StudentRegistrationDto;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import com.OnlineLearningPlatform.TheAkademiya.model.User;
import com.OnlineLearningPlatform.TheAkademiya.repository.StudentRepository;
import com.OnlineLearningPlatform.TheAkademiya.service.ArchiveService;
import com.OnlineLearningPlatform.TheAkademiya.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
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

    @PostMapping("/student/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDto registrationDto) {
        studentService.registerStudentWithUser(registrationDto.toStudent(), registrationDto.toUser());
        return ResponseEntity.ok("Student registered successfully");
    }

}
