package hu.NeptunApi.services;

import hu.NeptunApi.domain.Department;

import hu.NeptunApi.domain.Teacher;
import hu.NeptunApi.dto.NewTeacherRequest;
import hu.NeptunApi.repositories.DepartmentRepository;
import hu.NeptunApi.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;



    public List<Teacher> getTeachers() {
        return repository.nativeFindTeachers();
    }

    public Teacher getTeacher(int ID) {
        Optional<Teacher> teacher = repository.findById(ID);
        if (teacher.isPresent())
            return teacher.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }


    public Teacher updateTeacher(int ID, String name,Department department) {
        Optional<Teacher> optionalTeacher = repository.findById(ID);
        if(optionalTeacher.isPresent()){
            Teacher teacher = optionalTeacher.get();
            teacher.setName(name);
            teacher.setDepartment(department);
            return repository.save(teacher);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
/*
    public Teacher addTeacher(Teacher teacher){
        Optional<Teacher> optionalTeacher = repository.findById(teacher.getID());
        if(!optionalTeacher.isPresent()){
            return repository.save(teacher);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }


 */



    public void deleteTeacher(int ID) {
        Optional<Teacher> optionalTeacher = repository.findById(ID);
        if(optionalTeacher.isPresent()){
            repository.deleteById(ID);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Map<String, String> getTeacherDetails(int ID) {
        Teacher teacher = repository.findById(ID).orElse(null);

        if (teacher != null) {
            Map<String, String> details = new HashMap<>();
            details.put("teacherName", teacher.getName());

            if (teacher.getDepartment() != null) {
                details.put("departmentName", teacher.getDepartment().getName());
            }

            return details;
        } else {
            return null;
        }
    }


    public void addTeacherToExistingDepartment(NewTeacherRequest teacherRequest) {
        Department department = departmentRepository.findById(teacherRequest.getDepartment_ID())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + teacherRequest.getDepartment_ID()));

        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        teacher.setDepartment(department);

        repository.save(teacher);

        department.getTeachers().add(teacher); // hozzáadás az osztályhoz

        departmentRepository.save(department); // mentés
    }


    public Teacher updateTeacher(int ID, Map<String, Object> updates) {
        Teacher teacher = repository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + ID));

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
                case "name":
                    teacher.setName((String) value);
                    break;
                // Más mezők esetén további esetek hozzáadása
                default:
                    // Nem ismert mező esetén dobj kivételt vagy ne tedd semmit, attól függően, mi a preferenciád
                    throw new IllegalArgumentException("Unsupported field: " + field);
            }
        }

        repository.save(teacher);
        return teacher;
    }

    public Teacher updateTeacherDepartment(int ID, int newDepartmentId) {
        Teacher teacher = repository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + ID));

        if (newDepartmentId != 0) {
            Department newDepartment = departmentRepository.findById(newDepartmentId)
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + newDepartmentId));

            teacher.setDepartment(newDepartment);
        }

        return repository.save(teacher);
    }
    //add teacher, update teacher department, remove teacher department.....
}
