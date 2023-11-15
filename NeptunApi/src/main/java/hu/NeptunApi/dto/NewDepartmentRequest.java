package hu.NeptunApi.dto;

import hu.NeptunApi.domain.Department;

public class NewDepartmentRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department toDepartment(){
        Department department = new Department();
        department.setName(name);
        return department;

    }

    @Override
    public String toString() {
        return "NewDepartmentRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
