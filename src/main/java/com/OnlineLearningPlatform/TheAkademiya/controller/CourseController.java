package com.OnlineLearningPlatform.TheAkademiya.controller;

import com.OnlineLearningPlatform.TheAkademiya.model.Course;
import com.OnlineLearningPlatform.TheAkademiya.repository.CourseRepository;
import com.OnlineLearningPlatform.TheAkademiya.service.ArchiveService;
import com.OnlineLearningPlatform.TheAkademiya.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    ArchiveService archiveService;

    @PostMapping("/newCourse")
    Course newCourse(@RequestBody Course newCourse){
        return courseService.newCourse(newCourse);
    }

    @GetMapping("/allCourse")
    List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    @GetMapping("/course/{courseId}")
    Course getCourseById(@PathVariable Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(()-> new RuntimeException("Course Does not Exist"));
    }

    @PutMapping("/course/{courseId}")
    public Course updateCourse(@RequestBody Course newCourse, @PathVariable Long courseId){
        return courseService.updateCourse(courseId, newCourse);
    }

    @PutMapping("/courseArchive/{courseId}")
    public ResponseEntity<?> archiveCourse(@PathVariable Long courseId){
        archiveService.archiveCourse(courseId);
        return ResponseEntity.ok("Success");
    }

}
