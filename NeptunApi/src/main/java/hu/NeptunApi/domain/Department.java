package hu.NeptunApi.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Size(min = 1,max = 30, message = "Az ajtoszám min 1 max 30 karakter")
    @Column(unique = true)
    private String name;


    public Department() {
    }

    public Department(int ID, String name) {
        this.ID = ID;
        this.name = name;
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
}
