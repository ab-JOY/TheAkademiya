package com.OnlineLearningPlatform.TheAkademiya.service;

import com.OnlineLearningPlatform.TheAkademiya.model.Instructor;
import com.OnlineLearningPlatform.TheAkademiya.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    public Instructor updateInstructor(Long instructorId, Instructor newInstructor){
        return instructorRepository.findById(instructorId)
                .map(instructor -> {
                    instructor.setFirstName(newInstructor.getFirstName());
                    instructor.setLastName(newInstructor.getLastName());

                    return  instructorRepository.save(instructor);
                })
                .orElseThrow(()->new RuntimeException("Instructor not saved!!!"));
    }

    public Instructor newInstructor(Instructor newInstructor){
        return instructorRepository.save(newInstructor);
    }

    List<Instructor> getAllInstructor(){
        return instructorRepository.findAll();
    }

    Instructor getInstructorById(Long instructorId){
        return instructorRepository.findById(instructorId)
                .orElseThrow(()-> new RuntimeException("Instructor not Found!!!"));
    }
}
