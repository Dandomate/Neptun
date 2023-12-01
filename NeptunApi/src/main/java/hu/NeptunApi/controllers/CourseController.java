package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Course;

import hu.NeptunApi.domain.StudentCourseList;
import hu.NeptunApi.domain.TeacherCourseList;
import hu.NeptunApi.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import  hu.NeptunApi.dto.NewCourseRequest;

import javax.persistence.EntityNotFoundException;

@RestController
public class CourseController {
@Autowired
private CourseService service;



    @GetMapping("/courses")
    public List<Course> getCourses() {
        System.out.println("courses");
        return service.getCourse();
    }

    @GetMapping("/student/course/{neptun_code}/{days}")
    public List<StudentCourseList> getStudentCourseList(@PathVariable("neptun_code") String neptun_code, @PathVariable("days") String days) {
        return service.getStudentCourseList(neptun_code, days);
    }


    @GetMapping("/teacher/course/{neptun_code}/{days}")
    public List<TeacherCourseList> getTeacherCourseList(@PathVariable("neptun_code") String neptun_code, @PathVariable("days") String days) {
        return service.getTeacherCourseList(neptun_code, days);
    }

    @GetMapping("/course/{ID}")
    public Course getCourse(@PathVariable("ID") int ID) {
        System.out.println("course");
        return service.getCourse(ID);

    }


    @PatchMapping("/course/updatename/{ID}")
    public Course updateCourse(@PathVariable("ID") int ID, @RequestBody Course course) {
        String name = course.getName();


        return service.updateCourse(ID, name);
    }

    @DeleteMapping("/course/delete/{ID}")
    public ResponseEntity<String> deleteCourse(@PathVariable("ID") int ID) {
        service.deleteCourse(ID);
        return ResponseEntity.ok("Course delete successfully.");
    }




    @PostMapping("/courses/add")
    public ResponseEntity<String> addCourse(
            @RequestBody NewCourseRequest newCourseRequest) {
        try {
            service.addCourse(newCourseRequest);
            return ResponseEntity.ok("Course added successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


/*
    @PatchMapping("/course/updateAll/{ID}")
    public ResponseEntity<String> updateCourseAll(
            @PathVariable int ID,
            @RequestBody Map<String, Object> updates) {
        String name = (String) updates.get("name");
        String description = (String) updates.get("description");
        String day = (String) updates.get("day");
        int equipment_ID = (int) updates.get("equipment_ID");
        int classroom_ID = (int) updates.get("classroom_ID");
        int teacher_ID = (int) updates.get("teacher_ID");
        int student_ID = (int) updates.get("student_ID");
        try {
            Course updatedCourse = service.updateCourseAll(ID,name,description,day, equipment_ID,classroom_ID,teacher_ID,student_ID);
            return ResponseEntity.ok("Course updated successfully. New name: " + updatedCourse.getName());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

 */
}