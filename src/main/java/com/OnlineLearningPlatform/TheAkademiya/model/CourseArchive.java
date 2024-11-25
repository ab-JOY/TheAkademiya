package com.OnlineLearningPlatform.TheAkademiya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long archivedCourseId;

    private String titleArchived;
    private Integer creditArchived;

    @OneToOne(cascade = CascadeType.ALL)
    private CourseMaterial courseMaterialArchived;

    @ManyToOne(cascade = CascadeType.ALL)
    private Instructor instructorArchived;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> studentsArchived;
}
