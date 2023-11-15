package hu.NeptunApi.services;


import hu.NeptunApi.domain.ClassRoom;
import hu.NeptunApi.domain.ClassRoomList;
import hu.NeptunApi.dto.NewClassRoomRequest;
import hu.NeptunApi.repositories.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClassRoomService {

    @Autowired
    private ClassRoomRepository repository;


    public List<ClassRoomList> getClassRooms() {
        List<ClassRoomList> classrooms = new ArrayList<>();
        List<Object[]> data = repository.getClassRooms();
        for (Object[] object : data) {
            int ID = (Integer)object[0];
            String door = (String) object[1];
            int space = (Integer) object[2];
            classrooms.add(new ClassRoomList(ID, door, space));
        }
        System.out.println("classrooms service");
        return classrooms;
    }

    public ClassRoom getClassRoom(int ID){
        Optional<ClassRoom> classroom = repository.findById(ID);
        if(classroom.isPresent())
            return classroom.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public ClassRoom updateClassRoom(int ID,int space) {
        Optional<ClassRoom> optionalClassRoom = repository.findById(ID);
        if(optionalClassRoom.isPresent()){
            ClassRoom classroom = optionalClassRoom.get();
            classroom.setSpace(space);
            return repository.save(classroom);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public ClassRoom addClassRoom(NewClassRoomRequest newClassRoomRequest){
        ClassRoom classRoom=newClassRoomRequest.toClassRoom();
        System.out.println(newClassRoomRequest);
        return repository.save(classRoom);
    }


    public void deleteClassRoom(int ID) {
        Optional<ClassRoom> optionalClassRoom = repository.findById(ID);
        if(optionalClassRoom.isPresent()){
            repository.deleteById(ID);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}