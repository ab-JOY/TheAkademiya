package com.OnlineLearningPlatform.TheAkademiya.repository;

import com.OnlineLearningPlatform.TheAkademiya.model.StudentArchive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentArchivedRepository extends JpaRepository<StudentArchive, Long> {
}
