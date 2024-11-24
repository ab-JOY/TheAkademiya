package com.OnlineLearningPlatform.TheAkademiya.repository;

import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByGuardianName(String guardianName);
}
