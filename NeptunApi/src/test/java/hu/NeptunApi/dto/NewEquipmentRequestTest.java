package hu.NeptunApi.dto;

import hu.NeptunApi.domain.Equipment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NewEquipmentRequestTest {

    @Test
    public void testToEquipment() {
        // Arrange
        NewEquipmentRequest request = new NewEquipmentRequest("TestDesignation", 42, "TestDescription");

        // Act
        Equipment equipment = request.toEquipment();

        // Assert
        assertThat(equipment).isNotNull();
        assertThat(equipment.getDesignation()).isEqualTo("TestDesignation");
        assertThat(equipment.getQuantity()).isEqualTo(42);
        assertThat(equipment.getDescription()).isEqualTo("TestDescription");
    }
}