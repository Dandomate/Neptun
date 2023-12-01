package hu.NeptunApi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID ;
    @Size(min = 3,max = 30,message = "A név 3-30 közötti karakter legyen")
    @Column(name = "name")
    private String name;
    @Size(min = 1,max = 30,message = "A születés 1-30közötti karakter legyen igy add meg: 2001-10-10")
    @Column(name = "birth_date")
    private String birth_date;
    @Size(min = 1,max = 30,message = "A neptun 1-30 közötti karakter legyen")
    @Column(name = "neptun_code",unique = true)
    private String neptun_code;

    // Egy diákhoz több kurzus, és egy kurzushoz több diák

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Student(int ID, String name, String birth_date, String neptun_code) {
        this.ID = ID;
        this.name = name;
        this.birth_date = birth_date;
        this.neptun_code = neptun_code;
    }


    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getNeptun_code() {
        return neptun_code;
    }

    public void setNeptun_code(String neptun_code) {
        this.neptun_code = neptun_code;
    }

    public Student(int ID, String name, String birth_date, String neptun_code, List<Course> courses) {
        this.ID = ID;
        this.name = name;
        this.birth_date = birth_date;
        this.neptun_code = neptun_code;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}