package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Equipment;
import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.StudentList;
import hu.NeptunApi.domain.Teacher;
import hu.NeptunApi.dto.NewEquipmentRequest;
import hu.NeptunApi.dto.NewStudentRequest;
import hu.NeptunApi.dto.NewTeacherRequest;
import hu.NeptunApi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
@RestController
public class StudentController {

    @Autowired
    private StudentService service;
    @GetMapping("/students")
    public List<StudentList> getStudents(){
        return service.getStudents();
    }
    @GetMapping("/students/{ID}")
    public Student getStudent(@PathVariable("ID") int ID){
        System.out.println("teacher");
        return service.getStudent(ID);

    }
    @PatchMapping("/students/{ID}")
    public Student updateStudentName(@PathVariable("ID") Integer ID,
                                 @RequestBody Student student)
    {
        String name = student.getName();
        String birth_day = student.getBirth_date();
        String neptun_code=student.getNeptun_code();
        return service.updateStudent(ID, name,birth_day,neptun_code);
    }
    @DeleteMapping("/students/{ID}")
    public void deleteStudent(@PathVariable("ID") int ID){
        service.deleteStudent(ID);
    }


    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody NewStudentRequest newStudentRequest){
        return service.addStudent(newStudentRequest);
    }

}