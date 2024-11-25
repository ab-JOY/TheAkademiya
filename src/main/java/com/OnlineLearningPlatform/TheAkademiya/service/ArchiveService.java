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

    @Autowired
    private CourseArchiveRepository courseArchiveRepository;



    @Transactional
    public void StudentArchive(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        studentOptional.ifPresent(student -> {
            StudentArchive studentArchive = new StudentArchive();

            studentArchive.setFirstNameArchived(student.getFirstName());
            studentArchive.setLastNameArchived(student.getLastName());
            studentArchive.setCourse_archived(student.getCourse());
            studentArchive.setEmailId(student.getEmailId());
            studentArchive.setYearLevel(student.getYearLevel());

            Guardian guardian = student.getGuardian();
            if (guardian != null) {
                GuardianArchive guardianArchive = new GuardianArchive();
                guardianArchive.setNameArchived(guardian.getName());
                guardianArchive.setEmailArchived(guardian.getEmail());
                guardianArchive.setMobileArchived(guardian.getMobile());
                studentArchive.setGuardianArchive(guardianArchive);
            }

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

    @Transactional
    public void archiveCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        courseOptional.ifPresent(course -> {
            CourseArchive archivedCourse = new CourseArchive();
            archivedCourse.setTitleArchived(course.getTitle());
            archivedCourse.setCreditArchived(course.getCredit());

            if (course.getCourseMaterial() != null) {
                archivedCourse.setCourseMaterialArchived(course.getCourseMaterial());
            }

            if (course.getInstructor() != null) {
                archivedCourse.setInstructorArchived(course.getInstructor());
            }


            if (course.getStudents() != null && !course.getStudents().isEmpty()) {
                archivedCourse.setStudentsArchived(course.getStudents());
            }
            courseArchiveRepository.save(archivedCourse);

            // Delete the original Course
            courseRepository.deleteById(courseId);
        });
    }

}
