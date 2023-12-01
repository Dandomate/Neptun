package hu.NeptunApi.dto;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.Teacher;

public class NewTeacherRequest {


        private String name;

        private String neptun_code;
        private int department_ID; // Új mező az osztály azonosítójához


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDepartment_ID() {
            return department_ID;
        }

        public void setDepartment_ID(int department_ID) {
            this.department_ID = department_ID;
        }

    public String getNeptun_code() {
        return neptun_code;
    }

    public void setNeptun_code(String neptun_code) {
        this.neptun_code = neptun_code;
    }
}


