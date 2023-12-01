package hu.NeptunFrontend.domain;

public class TimeTableList {
    private int TIMETABLE_ID;
    private String name;
    private String sname;
    private String cname;
    private String door;
    private String day;
    private int lesson;
    private int CLASS_ID;
    private long OMA_TEACHER;

    public TimeTableList() {
    }


    public TimeTableList(int TIMETABLE_ID,String name,String sname,String cname,String door,String day,int lesson) {
        this.TIMETABLE_ID=TIMETABLE_ID;
        this.name=name;
        this.sname=sname;
        this.cname=cname;
        this.door=door;
        this.day=day;
        this.lesson = lesson;
    }

    public TimeTableList(int TIMETABLE_ID,String name,String sname,String cname,String door,String day,int lesson,long OMA_TEACHER) {
        this.TIMETABLE_ID=TIMETABLE_ID;
        this.name=name;
        this.sname=sname;
        this.cname=cname;
        this.door=door;
        this.day=day;
        this.lesson = lesson;
        this.OMA_TEACHER=OMA_TEACHER;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }



    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }


    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getTIMETABLE_ID() {
        return TIMETABLE_ID;
    }

    public void setTIMETABLE_ID(int TIMETABLE_ID) {
        this.TIMETABLE_ID = TIMETABLE_ID;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(int CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }
}
