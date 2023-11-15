package hu.NeptunApi.dto;


import hu.NeptunApi.domain.Equipment;

public class NewEquipmentRequest {
    private String designation;
    private int quantity;
    private String description;

    @Override
    public String toString() {
        return "NewEquipmentRequest{" +
                "designation='" + designation + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }

    public NewEquipmentRequest(String designation, int quantity, String description) {
        this.designation = designation;
        this.quantity = quantity;
        this.description = description;
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

    public Equipment toEquipment() {
        Equipment equipment =new Equipment();
        equipment.setDesignation(this.designation);
        equipment.setQuantity(this.quantity);
        equipment.setDescription(this.description);
        return equipment;
    }
}
