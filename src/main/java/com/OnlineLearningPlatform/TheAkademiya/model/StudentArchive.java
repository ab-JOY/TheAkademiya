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
        name = "tbl_student_archive",
        uniqueConstraints = @UniqueConstraint(
                name = "emailIdArchived_unique",
                columnNames = "email_address_archived"
        )
)
public class StudentArchive {
    @Id
    @SequenceGenerator(
            name = "student_archive_sequence",
            sequenceName = "student_archive_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_archive_sequence"
    )
    private Long studentArchivedId;
    private String firstNameArchived;
    private String lastNameArchived;
    private String course_archived;
    private Integer yearLevel;

    @Column(
            name = "email_address_archived",
            nullable = false
    )
    private String emailId;

    @Embedded
    private GuardianArchive guardianArchive;
}
