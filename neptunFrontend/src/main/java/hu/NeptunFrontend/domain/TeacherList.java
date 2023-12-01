package hu.NeptunFrontend.domain;

public class TeacherList {
    private int ID ;
    private String name;

    private String birth_date;
    private String neptun_code;

    public TeacherList() {
    }

    public TeacherList(int ID, String name, String birth_date, String neptun_code) {
        this.ID = ID;
        this.name = name;
        this.birth_date = birth_date;
        this.neptun_code = neptun_code;
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
