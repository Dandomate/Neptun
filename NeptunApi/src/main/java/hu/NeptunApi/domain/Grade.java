package hu.NeptunApi.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;


    @Min(value = 1,message = "érték 1 nél kisebb")
    @Max(value = 2, message = "érték 5 nál nagyobb")
    private int grade;

    public Grade() {
    }

    public Grade(int ID, int grade) {
        this.ID = ID;
        this.grade = grade;
    }

    public int getID() {
        return ID;
    }

    public int getGrade() {
        return grade;
    }

}
