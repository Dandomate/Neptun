package hu.NeptunApi.domain;

public class EquipmentList {
    private int ID;
    private String designation;
    private int quantity;
    private String description;

    public EquipmentList() {

    }

    public EquipmentList(int ID, String designation, int quantity, String description) {
        this.ID = ID;
        this.designation = designation;
        this.quantity = quantity;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
