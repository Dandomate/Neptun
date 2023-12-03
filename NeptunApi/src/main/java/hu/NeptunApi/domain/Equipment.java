package hu.NeptunApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Size(min = 1,max = 30, message = "A megnevezés min 1 max 30 karakter")
    private String designation;
    @Min(value = 1,message = "érték 1 nél kisebb")
    @Max(value = 30, message = "érték 30 nál nagyobb")
    private int quantity;
    @Size(min = 1,max = 100, message = "A megnevezés min 1 max 100 karakter")
    private String description;


    @JsonIgnore
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Equipment() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Equipment(int ID, String designation, int quantity, String description, List<Course> courses) {
        this.ID = ID;
        this.designation = designation;
        this.quantity = quantity;
        this.description = description;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Equipment(int ID, String designation, int quantity, String description) {
        this.ID = ID;
        this.designation = designation;
        this.quantity = quantity;
        this.description = description;
    }
}
