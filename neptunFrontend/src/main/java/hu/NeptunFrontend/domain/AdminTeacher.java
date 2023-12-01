package hu.NeptunFrontend.domain;


public class AdminTeacher {


    private long OMA_TEACHER;
    private String Password;
    private boolean Admin;


    public AdminTeacher() {
    }

    public AdminTeacher(long OMA_TEACHER,String Password,boolean Admin) {
        this.OMA_TEACHER = OMA_TEACHER;
        this.Password = Password;
        this.Admin = Admin;
    }

    public long getOMA_TEACHER() {
        return OMA_TEACHER;
    }

    public void setOMA_TEACHER(long OMA_TEACHER) {
        this.OMA_TEACHER = OMA_TEACHER;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        this.Admin = admin;
    }


}

