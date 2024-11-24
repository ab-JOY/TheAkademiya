package com.OnlineLearningPlatform.TheAkademiya.service;

import com.OnlineLearningPlatform.TheAkademiya.model.*;
import com.OnlineLearningPlatform.TheAkademiya.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ArchiveService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private StudentArchivedRepository studentArchivedRepository;

    @Autowired
    private InstructorArchiveRepository instructorArchiveRepository;



    @Transactional
    public void StudentArchive(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        studentOptional.ifPresent(student -> {
            StudentArchive studentArchive = new StudentArchive();

            studentArchive.setFirstNameArchived(student.getFirstName());
            studentArchive.setLastNameArchived(student.getLastName());
            studentArchive.setCourse_archived(student.getCourse());
//            studentArchive.setGuardianArchive(student.getGuardian());
            studentArchive.setEmailId(student.getEmailId());
            studentArchive.setYearLevel(student.getYearLevel());
            studentArchivedRepository.save(studentArchive);

            studentRepository.deleteById(studentId);
        });
    }


    @Transactional
    public void InstructorArchive(Long instructorId) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(instructorId);

        instructorOptional.ifPresent(instructor -> {
            InstructorArchived instructorArchived = new InstructorArchived();

            instructorArchived.setFirstName(instructor.getFirstName());
            instructorArchived.setLastName(instructor.getLastName());
            instructorArchiveRepository.save(instructorArchived);

            instructorRepository.deleteById(instructorId);
        });
    }
}
