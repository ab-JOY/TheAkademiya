package com.OnlineLearningPlatform.TheAkademiya.controller;

import com.OnlineLearningPlatform.TheAkademiya.model.Instructor;
import com.OnlineLearningPlatform.TheAkademiya.repository.InstructorRepository;
import com.OnlineLearningPlatform.TheAkademiya.service.ArchiveService;
import com.OnlineLearningPlatform.TheAkademiya.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/newInstructor")
    Instructor newInstructor(@RequestBody Instructor newInstructor){
        return instructorService.newInstructor(newInstructor);
    }

    @GetMapping("/allInstructor")
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

    @PutMapping("/instructorArchive/{instructorId}")
    public ResponseEntity<?> archiveInstructor(@PathVariable Long instructorId){
        archiveService.InstructorArchive(instructorId);
        return ResponseEntity.ok("Success");
    }
}
