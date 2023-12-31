package hu.NeptunFrontend.domain;

public class Department {
    private int ID;
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
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
