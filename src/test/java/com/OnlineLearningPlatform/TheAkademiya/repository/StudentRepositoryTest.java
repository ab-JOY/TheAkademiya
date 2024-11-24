package com.OnlineLearningPlatform.TheAkademiya.repository;

import com.OnlineLearningPlatform.TheAkademiya.model.Guardian;
import com.OnlineLearningPlatform.TheAkademiya.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
                .name("Kean")
                .email("kean@email")
                .mobile("09123456789")
                .build();

        Student student = Student.builder()
                .firstName("Jin")
                .lastName("Alba")
                .emailId("jin@email")
                .course("BSCS")
                .yearLevel(4)
                .guardian(guardian)
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