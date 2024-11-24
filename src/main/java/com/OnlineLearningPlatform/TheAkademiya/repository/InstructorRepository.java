package com.OnlineLearningPlatform.TheAkademiya.repository;

import com.OnlineLearningPlatform.TheAkademiya.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
