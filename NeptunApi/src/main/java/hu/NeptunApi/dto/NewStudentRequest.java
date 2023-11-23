package hu.NeptunApi.dto;

import hu.NeptunApi.domain.Student;

public class NewStudentRequest {
    private int ID;

    private String name;

    private String birth_date;

    private String neptun_code;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student toStudent() {
        Student student = new Student();
        student.setName(this.name);
        student.setBirth_date(this.birth_date);
        student.setNeptun_code(this.neptun_code);
        return student;

    }
}
