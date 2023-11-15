package hu.NeptunApi.services;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.DepartmentList;
import hu.NeptunApi.dto.NewDepartmentRequest;
import hu.NeptunApi.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentList> getDepartments() {
        List<DepartmentList> departments = new ArrayList<>();
        List<Object[]> data = repository.getDepartments();
        for (Object[] object : data) {
            int ID = (Integer)object[0];
            String name = (String) object[1];
            departments.add(new DepartmentList(ID, name));
        }
        System.out.println("Departments service");
        return departments;
    }

    public Department getDepartment(int ID){
        Optional<Department> department = repository.findById(ID);
        if(department.isPresent())
            return department.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Department updateDepartment(int ID,String name) {
        Optional<Department> optionalDepartment = repository.findById(ID);
        if(optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();
            department.setName(name);
            return repository.save(department);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public Department addDepartment(NewDepartmentRequest newDepartmentRequest){
        Department department=newDepartmentRequest.toDepartment();
        System.out.println(newDepartmentRequest);
        return repository.save(department);
    }

    public void deleteDepartment(int ID) {
        Optional<Department> optionalDepartment = repository.findById(ID);
        if(optionalDepartment.isPresent()){
            repository.deleteById(ID);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
