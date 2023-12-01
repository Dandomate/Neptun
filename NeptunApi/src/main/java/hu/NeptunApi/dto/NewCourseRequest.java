package hu.NeptunApi.dto;

public class NewCourseRequest {
    private String name;
    private String description;
    private String day;
    private int equipment_ID;
    private int classroom_ID;
    private int teacher_ID;
    private int student_ID;


    public int getEquipment_ID() {
        return equipment_ID;
    }

    public void setEquipment_ID(int equipment_ID) {
        this.equipment_ID = equipment_ID;
    }

    public int getClassroom_ID() {
        return classroom_ID;
    }

    public void setClassroom_ID(int classroom_ID) {
        this.classroom_ID = classroom_ID;
    }

    public int getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(int teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
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
}