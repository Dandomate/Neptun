package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.Teacher;
import hu.NeptunApi.dto.NewTeacherRequest;
import hu.NeptunApi.repositories.DepartmentRepository;
import hu.NeptunApi.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService service;



    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        System.out.println("teachers");
        return service.getTeachers();
    }

    @GetMapping("/teachers/{ID}")
    public Teacher getTeacher(@PathVariable("ID") int ID) {
        System.out.println("teacher");
        return service.getTeacher(ID);

    }

/*

    @PatchMapping("/teachers/update/{ID}")
    public Teacher updateTeacher(@PathVariable("ID") int ID, @RequestBody Teacher teacher) {
        String name = teacher.getName();
        String neptun_code = teacher.getNeptun_code();
        Department department = teacher.getDepartment();

        return service.updateTeacher(ID,neptun_code, name, department);
    }


 */
    @DeleteMapping("/teachers/delete/{ID}")
    public void deleteTeacher(@PathVariable("ID") int ID) {
        service.deleteTeacher(ID);
    }

    @GetMapping("/teachers/department/{ID}")
    public ResponseEntity<Map<String, String>> getTeacherDetails(@PathVariable int ID) {
        Map<String, String> details = service.getTeacherDetails(ID);

        if (details != null && !details.isEmpty()) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/teachers/add")
    public ResponseEntity<String> addTeacherToExistingDepartment(
            @RequestBody NewTeacherRequest teacherRequest) {
        try {
            service.addTeacherToExistingDepartment(teacherRequest);
            return ResponseEntity.ok("Teacher added successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/teachers/name/update/{ID}")
    public ResponseEntity<String> updateTeacher(
            @PathVariable int ID,
            @RequestBody Map<String, Object> updates) {
        try {
            Teacher updatedTeacher = service.updateTeacher(ID, updates);
            return ResponseEntity.ok("Teacher updated successfully. New name: " + updatedTeacher.getName());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/teachers/department/update/{ID}")
    public ResponseEntity<Teacher> updateTeacherDepartment(
            @PathVariable int ID,
            @RequestBody Map<String, Object> payload) {
        int department_ID = (int) payload.get("department_ID");
        {
            try {
                Teacher updatedTeacher = service.updateTeacherDepartment(ID, department_ID);
                return ResponseEntity.ok(updatedTeacher);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }}
}