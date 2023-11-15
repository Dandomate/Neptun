package hu.NeptunApi.controllers;


import hu.NeptunApi.domain.Grade;
import hu.NeptunApi.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeController {
    @Autowired
    private GradeService service;
    @GetMapping("/grades/{ID}")
    public Grade getGrade(@PathVariable("ID") int ID){
        return service.getGrade(ID);
    }
}
