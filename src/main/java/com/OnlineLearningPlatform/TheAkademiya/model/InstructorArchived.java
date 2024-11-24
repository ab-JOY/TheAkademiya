package com.OnlineLearningPlatform.TheAkademiya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_instructor_archive"
)
public class InstructorArchived {

    @Id
    @SequenceGenerator(
            name = "instructor_archive_sequence",
            sequenceName = "instructor_archive_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructor_archive_sequence"
    )
    private Long instructorArchivedId;
    private String firstName;
    private String lastName;
}
