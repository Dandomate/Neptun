package hu.NeptunFrontend.domain;

public class ClassRoomList {
    private int ID;
    private String door;
    private int space;

    public ClassRoomList() {
    }

    public ClassRoomList(int ID, String door, int space) {
        this.ID = ID;
        this.door = door;
        this.space = space;
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
