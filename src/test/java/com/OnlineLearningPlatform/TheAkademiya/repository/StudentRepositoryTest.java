package com.OnlineLearningPlatform.TheAkademiya.repository;

import com.OnlineLearningPlatform.TheAkademiya.model.Guardian;
import com.OnlineLearningPlatform.TheAkademiya.model.Roles;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import com.OnlineLearningPlatform.TheAkademiya.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("allenbueza@gmail.com")
                .firstName("Allen")
                .lastName("Bueza")
//                .guardianName("Eugen")
//                .guardianEmail("eugen@gmal.com")
//                .guardianMobile("09123456789")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Joy")
                .email("joy@email")
                .mobile("098721365321")
                .build();

        Student student = Student.builder()
                .firstName("Aivan")
                .lastName("Bueza")
                .emailId("aivan@email")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian2(){

        Guardian guardian = Guardian.builder()
                .name("Jane Doe")
                .email("janedoe@email")
                .mobile("09123456789")
                .build();

        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("johndoe@email")
                .course("BSCS")
                .yearLevel(4)
                .guardian(guardian)
                .build();

        Roles studentRole = Roles.builder()
                .roleName("ROLE_STUDENT")
                .build();

        User user = User.builder()
                .username("johndoe123")
                .password("12345john")
                .roles(Set.of(studentRole))
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList" + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> student = studentRepository.findByFirstName("Aivan");

        System.out.println("student: " + student);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> student = studentRepository.findByFirstNameContaining("A");

        System.out.println("student: " + student);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> student = studentRepository.findByGuardianName("Joy");

        System.out.println("student" + student);
    }


}