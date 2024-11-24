package com.OnlineLearningPlatform.TheAkademiya.service;

import com.OnlineLearningPlatform.TheAkademiya.model.Course;
import com.OnlineLearningPlatform.TheAkademiya.model.CourseMaterial;
import com.OnlineLearningPlatform.TheAkademiya.model.Instructor;
import com.OnlineLearningPlatform.TheAkademiya.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course updateCourse(Long courseId, Course newCourse){
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.setTitle(newCourse.getTitle());
                    course.setCredit(newCourse.getCredit());

                    Instructor newInstructor = newCourse.getInstructor();
                    if(newInstructor != null){
                        Instructor existingInstructor = course.getInstructor();
                        if(existingInstructor != null){
                            existingInstructor.setFirstName(newInstructor.getFirstName());
                            existingInstructor.setLastName(newInstructor.getLastName());
                        } else{
                            course.setInstructor(newInstructor);
                        }
                    }

                    CourseMaterial newCourseMaterial = newCourse.getCourseMaterial();
                    if(newCourseMaterial != null){
                        CourseMaterial existingCourseMaterial = course.getCourseMaterial();
                        if(existingCourseMaterial != null){
                            existingCourseMaterial.setUrl(newCourseMaterial.getUrl());
                            existingCourseMaterial.setCourse(newCourseMaterial.getCourse());
                        } else{
                            course.setCourseMaterial(newCourseMaterial);
                        }
                    }

                    return courseRepository.save(course);
                })
                .orElseThrow(()-> new RuntimeException("Course not Saved"));
    }

    public Course newCourse(Course newCourse){
        return courseRepository.save(newCourse);
    }

    List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    Course getCourseById(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(()-> new RuntimeException("Course does not exist"));
    }
}
