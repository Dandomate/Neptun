package hu.NeptunApi.controllers;


import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.StudentList;

import hu.NeptunApi.dto.NewStudentRequest;

import hu.NeptunApi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
@RestController
public class StudentController {

    @Autowired
    private StudentService service;
    @GetMapping("/students")
    public List<StudentList> getStudents(){
        return service.getStudents();
    }
    @GetMapping("/student/{ID}")
    public Student getStudent(@PathVariable("ID") int ID){
        return service.getStudent(ID);

    }
    @PatchMapping("/student/update/{ID}")
    public Student updateStudentName(@PathVariable("ID") Integer ID,
                                 @RequestBody Student student)
    {
        String name = student.getName();
        String birth_day = student.getBirth_date();
        String neptun_code=student.getNeptun_code();
        return service.updateStudent(ID, name,birth_day,neptun_code);
    }
    @DeleteMapping("/students/delete/{ID}")
    public ResponseEntity<String> deleteStudent(@PathVariable("ID") int ID){
        service.deleteStudent(ID);
        return ResponseEntity.ok("Student delete successfully.");
    }


    @PostMapping("/students/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody NewStudentRequest newStudentRequest){
        return service.addStudent(newStudentRequest);
    }

}