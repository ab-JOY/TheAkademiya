package com.OnlineLearningPlatform.TheAkademiya.service;

import com.OnlineLearningPlatform.TheAkademiya.dto.UserRegistrationDto;
import com.OnlineLearningPlatform.TheAkademiya.model.Guardian;
import com.OnlineLearningPlatform.TheAkademiya.model.Instructor;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import com.OnlineLearningPlatform.TheAkademiya.model.User;
import com.OnlineLearningPlatform.TheAkademiya.repository.InstructorRepository;
import com.OnlineLearningPlatform.TheAkademiya.repository.StudentRepository;
import com.OnlineLearningPlatform.TheAkademiya.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final InstructorRepository instructorRepository;

    public UserService(UserRepository userRepository,
                       StudentRepository studentRepository,
                       PasswordEncoder passwordEncoder, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.instructorRepository = instructorRepository;
    }

    public void registerUser(UserRegistrationDto registrationDto) {
        // Create User
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(registrationDto.getRole());

        // Save the User entity first
        User savedUser = userRepository.save(user);

        if ("STUDENT".equalsIgnoreCase(registrationDto.getRole())) {
            // Create and save Student
            Student student = new Student();
            student.setFirstName(registrationDto.getFirstName());
            student.setLastName(registrationDto.getLastName());
            student.setCourse(registrationDto.getCourse());
            student.setYearLevel(registrationDto.getYearLevel());
            student.setEmailId(registrationDto.getEmailId());

            Guardian newGuardian = registrationDto.getGuardian();
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

            student.setUser(savedUser); // Associate Student with User

            // Save the Student entity
            studentRepository.save(student);

        } else if ("INSTRUCTOR".equalsIgnoreCase(registrationDto.getRole())) {
            // Create and save Instructor
            Instructor instructor = new Instructor();
            instructor.setFirstName(registrationDto.getFirstName());
            instructor.setLastName(registrationDto.getLastName());
            instructor.setUser(savedUser);
            instructorRepository.save(instructor);
        }


    }
}
