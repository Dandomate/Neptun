package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Equipment;
import hu.NeptunApi.domain.EquipmentList;
import hu.NeptunApi.dto.NewEquipmentRequest;
import hu.NeptunApi.services.EquipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentControllerTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    @Test
    public void testGetEquipments() {

        // Arrange
        List<EquipmentList> expectedEquipments = Arrays.asList(
                new EquipmentList(),
                new EquipmentList()
        );
        when(equipmentService.getEquipments()).thenReturn(expectedEquipments);

        // Act
        List<EquipmentList> actualEquipments = equipmentController.getEquipments();

        // Assert
        assertEquals(expectedEquipments, actualEquipments);
    }

    @Test
    public void testGetEquipment() {
        // Arrange
        int equipmentId = 1;
        String designation = "Equipment1";
        int quantity = 5;
        String description = "Description";
        Equipment expectedEquipment = new Equipment(equipmentId,  designation, quantity, description);

        when(equipmentService.getEquipment(equipmentId)).thenReturn(expectedEquipment);

        // Act
        Equipment actualEquipment = equipmentController.getEquipment(equipmentId);

        // Assert
        assertEquals(expectedEquipment.getID(), actualEquipment.getID());
        assertEquals(expectedEquipment.getQuantity(), actualEquipment.getQuantity());
        assertEquals(expectedEquipment.getDesignation(), actualEquipment.getDesignation());
        assertEquals(expectedEquipment.getDescription(), actualEquipment.getDescription());
    }
    @Test
    public void testAddEquipment() {
        // Arrange
        NewEquipmentRequest newEquipmentRequest = new NewEquipmentRequest("",0,"");
        newEquipmentRequest.setDesignation("DefaultDesignation");
        newEquipmentRequest.setQuantity(1);
        newEquipmentRequest.setDescription("DefaultDescription");

        // Act
        Equipment addedEquipment = equipmentController.addEquipment(newEquipmentRequest);

        // Assert
        verify(equipmentService, times(1)).addEquipment(newEquipmentRequest);
        assertEquals(HttpStatus.CREATED, ResponseEntity.status(HttpStatus.CREATED).build().getStatusCode());
    }

    @Test
    public void testUpdateEquipment() {
        // Arrange
        int equipmentId = 1;
        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setDesignation("UpdatedEquipment");
        updatedEquipment.setQuantity(10);
        updatedEquipment.setDescription("UpdatedDescription");

        // Act
        Equipment result = equipmentController.updateEquipment(equipmentId, updatedEquipment);

        // Assert
        verify(equipmentService, times(1)).updateEquipment(eq(equipmentId), anyString(), anyInt(), anyString());
        // Add additional assertions based on the expected behavior of the update operation
    }

    @Test
    public void testDeleteEquipment() {
        // Arrange
        int equipmentId = 1;

        // Act
        ResponseEntity<String> response = equipmentController.deleteEquipment(equipmentId);

        // Assert
        verify(equipmentService, times(1)).deleteEquipment(eq(equipmentId));
        assertEquals("Equipment delete successfully.", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}