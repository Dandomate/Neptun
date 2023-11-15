package hu.NeptunApi.services;

import hu.NeptunApi.domain.Equipment;
import hu.NeptunApi.domain.EquipmentList;
import hu.NeptunApi.dto.NewEquipmentRequest;
import hu.NeptunApi.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repository;


    public List<EquipmentList> getEquipments() {
        List<EquipmentList> equipments = new ArrayList<>();
        List<Object[]> data = repository.getEquipment();
        for (Object[] object : data) {
            int ID = (Integer)object[0];
            String designation = (String) object[1];
            int quantity = (Integer) object[2];
            String description = (String) object[3];
            equipments.add(new EquipmentList(ID, designation,quantity, description));
        }
        System.out.println("Equipments service");
        return equipments;
    }

    public Equipment getEquipment(int ID){
        Optional<Equipment> equipment = repository.findById(ID);
        if(equipment.isPresent())
            return equipment.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Equipment updateEquipment(int ID,String designation,int quantity,String description) {
        Optional<Equipment> optionalEquipment = repository.findById(ID);
        if(optionalEquipment.isPresent()){
            Equipment equipment = optionalEquipment.get();
            equipment.setDesignation(designation);
            equipment.setQuantity(quantity);
            equipment.setDescription(description);
            return repository.save(equipment);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public Equipment addEquipment(NewEquipmentRequest newEquipmentRequest){
        Equipment equipment=newEquipmentRequest.toEquipment();
        System.out.println(newEquipmentRequest);
        return repository.save(equipment);
    }


    public void deleteEquipment(int ID) {
        Optional<Equipment> optionalEquipment = repository.findById(ID);
        if(optionalEquipment.isPresent()){
            repository.deleteById(ID);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}