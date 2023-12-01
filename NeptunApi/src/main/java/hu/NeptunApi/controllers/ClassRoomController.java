package hu.NeptunApi.controllers;


import hu.NeptunApi.domain.ClassRoom;
import hu.NeptunApi.domain.ClassRoomList;
import hu.NeptunApi.dto.NewClassRoomRequest;
import hu.NeptunApi.services.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class ClassRoomController {

    @Autowired
    private ClassRoomService service;

    @GetMapping("/classrooms")
    public List<ClassRoomList> getClassRooms(){
        System.out.println("classrooms controller");
        return service.getClassRooms();
    }

    @GetMapping("/classroom/{ID}")
    public ClassRoom getClassRoom(@PathVariable("ID") int ID){
        return service.getClassRoom(ID);
    }

    @PostMapping("/classrooms/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassRoom addClassRoom(@RequestBody NewClassRoomRequest newClassRoomRequest){
        return service.addClassRoom(newClassRoomRequest);
    }

    @PatchMapping("/classrooms/updatespace/{ID}")
    public ClassRoom updateClassRoom(@PathVariable("ID") int ID, @RequestBody ClassRoom classroom){
        Integer space = classroom.getSpace();
        return service.updateClassRoom(ID,space);
    }

    @DeleteMapping("/classrooms/delete/{ID}")
    public ResponseEntity<String> deleteClassRoom(@PathVariable("ID") int ID){
        service.deleteClassRoom(ID);
        return ResponseEntity.ok("ClassRoom delete successfully.");
    }

}