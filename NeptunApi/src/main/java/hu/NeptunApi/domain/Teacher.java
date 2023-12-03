package hu.NeptunApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int ID;
    @Size(min = 5,max = 30,message = "Your Neptune code min 5 characters and max 30 characters")
    @Column(name = "neptun_code",unique = true)
    private String neptun_code;
    @NotNull
    @Size(min = 5,max = 30,message = "Your Name min 5 characters and max 30 characters")
    private String name;



    @ManyToOne
    @JoinColumn(name = "department_ID")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();



    public Teacher() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getNeptun_code() {
        return neptun_code;
    }

    public void setNeptun_code(String neptun_code) {
        this.neptun_code = neptun_code;
    }

    public Teacher(int ID, String neptun_code, String name, Department department, List<Course> courses) {
        this.ID = ID;
        this.neptun_code = neptun_code;
        this.name = name;
        this.department = department;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
