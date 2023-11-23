package hu.NeptunApi.dto;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.Teacher;

public class NewTeacherRequest {


        private String name;
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
    }


/*

    public Teacher toTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName(this.name);

        // Itt ellenőrizzük, hogy a departmentId ne legyen null és nagyobb legyen, mint 0
        if (this.department_ID > 0) {
            // Beállítjuk a Teacher osztályhoz tartozó Department objektumot
            Department department = new Department();
            department.setID(this.department_ID);
            teacher.setDepartment(department);
        }

        return teacher;
    }

 */
