package com.OnlineLearningPlatform.TheAkademiya.service;

import com.OnlineLearningPlatform.TheAkademiya.model.Guardian;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import com.OnlineLearningPlatform.TheAkademiya.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student updateStudent(Long studentId, Student newStudent) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    student.setFirstName(newStudent.getFirstName());
                    student.setLastName(newStudent.getLastName());
                    student.setEmailId(newStudent.getEmailId());
                    student.setCourse(newStudent.getCourse());
                    student.setYearLevel(newStudent.getYearLevel());

                    Guardian newGuardian = newStudent.getGuardian();
                    if (newGuardian != null) {
                        Guardian existingGuardian = student.getGuardian();
                        if (existingGuardian != null) {
                            existingGuardian.setName(newGuardian.getName());
                            existingGuardian.setEmail(newGuardian.getEmail());
                            existingGuardian.setMobile(newGuardian.getMobile());
                        } else {
                            student.setGuardian(newGuardian);
                        }
                    }

                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not saved!!!"));
    }

    public Student newStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student does not exist!!!"));
    }
}
