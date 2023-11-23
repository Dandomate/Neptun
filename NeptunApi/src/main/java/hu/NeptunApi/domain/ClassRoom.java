package hu.NeptunApi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Min(value = 1,message = "érték 1 nél kisebb")
    @Max(value = 100, message = "érték 100 nál nagyobb")
    private int space;
    @JsonIgnore
    @OneToMany(mappedBy = "classroom")
    private List<Course> courses;


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


}