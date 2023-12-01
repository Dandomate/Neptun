package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Equipment;
import hu.NeptunApi.domain.EquipmentList;
import hu.NeptunApi.dto.NewEquipmentRequest;
import hu.NeptunApi.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService service;

    @GetMapping("/equipments")
    public List<EquipmentList> getEquipments(){
        System.out.println("Equipment controller");
        return service.getEquipments();
    }

    @GetMapping("/equipment/{ID}")
    public Equipment getEquipment(@PathVariable("ID") int ID){
        return service.getEquipment(ID);
    }

    @PostMapping("/equipments/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment addEquipment(@RequestBody NewEquipmentRequest newEquipmentRequest){
        return service.addEquipment(newEquipmentRequest);
    }

    @PatchMapping("/equipments/update/{ID}")
    public Equipment updateEquipment(@PathVariable("ID") int ID, @RequestBody Equipment equipment){
        String designation = equipment.getDesignation();
        int quantity =equipment.getQuantity();
        String description = equipment.getDescription();
        return service.updateEquipment(ID,designation,quantity,description);
    }

    @DeleteMapping("/equipment/delete/{ID}")
    public void deleteEquipment(@PathVariable("ID") int ID){
        service.deleteEquipment(ID);
    }
}

