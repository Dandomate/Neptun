package hu.NeptunApi.dto;


import hu.NeptunApi.domain.ClassRoom;

public class NewClassRoomRequest {
    private String door;
    private int space;

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

    public ClassRoom toClassRoom(){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setDoor(this.door);
        classRoom.setSpace(this.space);
        return classRoom;

    }


    @Override
    public String toString() {
        return "NewClassRoomRequest{" +
                "door='" + door + '\'' +
                ", space=" + space +
                '}';
    }
}
