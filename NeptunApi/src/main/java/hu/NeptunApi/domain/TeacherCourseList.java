package hu.NeptunApi.domain;

public class TeacherCourseList {
    int ID;
    private String name;
    private String description;
    private String day;
    private String designation;
    private String door;
    private String teachername;
    private String studentname;

    public TeacherCourseList() {
    }

    public TeacherCourseList(int ID, String name, String description, String day, String designation, String door, String teachername, String studentname) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.day = day;
        this.designation = designation;
        this.door = door;
        this.teachername = teachername;
        this.studentname = studentname;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
