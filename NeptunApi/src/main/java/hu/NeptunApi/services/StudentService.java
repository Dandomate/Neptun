package hu.NeptunApi.services;

import hu.NeptunApi.domain.Equipment;
import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.StudentList;
import hu.NeptunApi.dto.NewEquipmentRequest;
import hu.NeptunApi.dto.NewStudentRequest;
import hu.NeptunApi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;


    public Student getStudent(int ID) {
        Optional<Student> student = repository.findById(ID);
        if (student.isPresent())
            return student.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public List<StudentList> getStudents() {
        List<StudentList> students = new ArrayList<>();
        List<Object[]> data = repository.getStudents();
        for (Object[] object : data) {
            Integer ID = (Integer) object[0];
            String name = (String) object[1];
            String birth_date = (String) object[2];
            String neptun_code = (String) object[3];
            students.add(new StudentList(ID, name, birth_date,neptun_code));
        }
        return students;
    }
    public Student updateStudent(int ID, String name, String birth_date,String neptun_Code) {
        Optional<Student> optionalStudent = repository.findById(ID);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setName(name);
            student.setBirth_date(birth_date);
            student.setNeptun_code(neptun_Code);
            return repository.save(student);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public Student addStudent(NewStudentRequest newStudentRequest){
        Student student=newStudentRequest.toStudent();
        System.out.println(newStudentRequest);
        return repository.save(student);
    }


    public void deleteStudent(int ID) {
        Optional<Student> optionalStudent = repository.findById(ID);
        if(optionalStudent.isPresent()){
            repository.deleteById(ID);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}