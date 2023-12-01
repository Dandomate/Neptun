package hu.NeptunFrontend.domain;

public class ClassRoom {
    private int ID;
    private String door;
    private int space;



    public ClassRoom(int ID, String door, int space) {
        this.ID = ID;
        this.door = door;
        this.space = space;
    }

    public ClassRoom(int ID, int space) {
        this.ID = ID;
        this.space = space;
    }

    public ClassRoom(String door, int space) {
        this.door=door;
        this.space = space;
    }




    public ClassRoom() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
}
