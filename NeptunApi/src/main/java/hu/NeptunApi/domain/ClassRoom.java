package hu.NeptunApi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classroom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Size(min = 1,max = 10, message = "Az ajtoszám min 1 max 10 karakter")
    @Column(unique = true)
    private String door;
    @Min(value = 1,message = "Érték 1 nél kisebb")
    @Max(value = 100, message = "Érték 100 nál nagyobb")
    private int space;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();



    public ClassRoom(int ID, String door, int space) {
        this.ID = ID;
        this.door = door;
        this.space = space;
    }

    public ClassRoom() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public void setDoor(String door) {
        this.door = door;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }


    public String getDoor() {
        return door;
    }

    public ClassRoom(int ID, String door, int space, List<Course> courses) {
        this.ID = ID;
        this.door = door;
        this.space = space;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}