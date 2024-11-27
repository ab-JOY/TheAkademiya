package com.OnlineLearningPlatform.TheAkademiya.dto;

import com.OnlineLearningPlatform.TheAkademiya.model.Guardian;
import com.OnlineLearningPlatform.TheAkademiya.model.Roles;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import com.OnlineLearningPlatform.TheAkademiya.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class StudentRegistrationDto {
    private String firstName;
    private String lastName;
    private String emailId;
    private String course;
    private Integer yearLevel;
    private String guardianName;
    private String guardianEmail;
    private String guardianMobile;
    private String username;
    private String password;

    public Student toStudent() {
        Guardian guardian = Guardian.builder()
                .name(this.guardianName)
                .email(this.guardianEmail)
                .mobile(this.guardianMobile)
                .build();

        return Student.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .emailId(this.emailId)
                .course(this.course)
                .yearLevel(this.yearLevel)
                .guardian(guardian)
                .build();
    }

    public User toUser() {
        Roles studentRole = Roles.builder()
                .roleName("ROLE_STUDENT")
                .build();

        return User.builder()
                .username(this.username)
                .password(this.password)
                .roles(Set.of(studentRole))
                .build();
    }


}
