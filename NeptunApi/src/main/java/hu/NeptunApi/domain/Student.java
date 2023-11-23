package hu.NeptunApi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private String birth_date;
    @Column(name = "neptun_code")
    private String neptun_code;

    // Egy diákhoz több kurzus, és egy kurzushoz több diák
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Student> student;
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


}