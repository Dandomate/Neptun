package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Course;

import hu.NeptunApi.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
@Autowired
    private CourseService service;



    @GetMapping("/courses")
    public List<Course> getCourses() {
        System.out.println("courses");
        return service.getCourse();
    }

    @GetMapping("/course/{ID}")
    public Course getCourse(@PathVariable("ID") int ID) {
        System.out.println("course");
        return service.getCourse(ID);

    }




    @PatchMapping("/course/{ID}")
    public Course updateCourse(@PathVariable("ID") int ID, @RequestBody Course course) {
        String name = course.getName();


        return service.updateCourse(ID, name);
    }

    @DeleteMapping("/Course/{ID}")
    public void deleteCourse(@PathVariable("ID") int ID) {
        service.deleteCourse(ID);
    }
/*
    @GetMapping("/teachersdep/{ID}")
    public ResponseEntity<Map<String, String>> getTeacherDetails(@PathVariable int ID) {
        Map<String, String> details = service.getTeacherDetails(ID);

        if (details != null && !details.isEmpty()) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



 */

}