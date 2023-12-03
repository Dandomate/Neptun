package hu.NeptunApi.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int ID;
    @Size(min = 1,max = 20, message = "Az name min 1 max 10 karakter")
    @Column(unique = true)
    private String name;
    @Size(min = 1, max = 30, message = "Az leírás min 0 max 30 karakter")
    @Column(unique = true)
    private String description;

    @Size(min = 1,max = 10, message = "A nap 1-10 közötti karakter")
    @Column(unique = true)
    private String day;



    @ManyToOne
    @JoinColumn(name = "equipment_ID")
    private Equipment equipment;


    @ManyToOne
    @JoinColumn(name = "classroom_ID")
    private ClassRoom classroom;

    @ManyToOne
    @JoinColumn(name = "teacher_ID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_ID")
    private Student student;


    public Course() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public ClassRoom getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassRoom classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course(int ID, String name, String description, String day) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.day = day;
    }
}
