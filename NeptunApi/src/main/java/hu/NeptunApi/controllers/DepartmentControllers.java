package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.DepartmentList;
import hu.NeptunApi.dto.NewDepartmentRequest;
import hu.NeptunApi.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentControllers {
    @Autowired
    private DepartmentService service;

    @GetMapping("/departments")
    public List<DepartmentList> getDepartments(){
        System.out.println("Departments controller");
        return service.getDepartments();
    }

    @GetMapping("/department/{ID}")
    public Department getDepartment(@PathVariable("ID") int ID){
        return service.getDepartment(ID);
    }

    @PostMapping("/departments/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Department addDepartment(@RequestBody NewDepartmentRequest newDepartmentRequest){
        return service.addDepartment(newDepartmentRequest);
    }

    @PatchMapping("/department/updatename/{ID}")
    public Department updateDepartment(@PathVariable("ID") int ID, @RequestBody Department department){
        String name = department.getName();
        return service.updateDepartment(ID,name);
    }

    @DeleteMapping("/department/delete/{ID}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("ID") int ID){
        service.deleteDepartment(ID);
        return ResponseEntity.ok("Department delete successfully.");
    }
}
