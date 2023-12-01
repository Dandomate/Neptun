package hu.NeptunFrontend.domain;

public class Equipment {
    private int ID;
    private String designation;
    private int quantity;

    private String description;

    public Equipment() {
    }

    public Equipment(int ID, String designation, int quantity, String description) {
        this.ID = ID;
        this.designation = designation;
        this.quantity = quantity;
        this.description = description;
    }

    public Equipment(String designation, int quantity, String description) {
        this.designation = designation;
        this.quantity = quantity;
        this.description = description;
    }

    public Equipment(int ID, String designation, String description) {
        this.ID = ID;
        this.designation = designation;
        this.description = description;
    }

    public Equipment(int quantity) {
        this.quantity = quantity;
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
