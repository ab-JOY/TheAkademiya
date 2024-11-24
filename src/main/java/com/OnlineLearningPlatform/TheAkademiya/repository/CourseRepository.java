package com.OnlineLearningPlatform.TheAkademiya.repository;

import com.OnlineLearningPlatform.TheAkademiya.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
