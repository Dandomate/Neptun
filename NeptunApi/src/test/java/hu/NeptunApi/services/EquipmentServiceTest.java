package hu.NeptunApi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hu.NeptunApi.domain.Equipment;
import hu.NeptunApi.domain.EquipmentList;
import hu.NeptunApi.dto.NewEquipmentRequest;
import hu.NeptunApi.repositories.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @InjectMocks
    private EquipmentService equipmentService;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Test
    void testGetEquipments() {
        // Simulate equipment data
        Object[] equipment1Data = {1, "Designation1", 10, "Description1"};
        Object[] equipment2Data = {2, "Designation2", 15, "Description2"};
        List<Object[]> mockData = List.of(equipment1Data, equipment2Data);

        // Mock the repository
        when(equipmentRepository.getEquipment()).thenReturn(mockData);

        // Test the method
        List<EquipmentList> result = equipmentService.getEquipments();

        // Verify the result
        assertEquals(2, result.size());

        EquipmentList equipmentList1 = result.get(0);
        assertEquals(1, equipmentList1.getID());
        assertEquals("Designation1", equipmentList1.getDesignation());
        assertEquals(10, equipmentList1.getQuantity());
        assertEquals("Description1", equipmentList1.getDescription());

        EquipmentList equipmentList2 = result.get(1);
        assertEquals(2, equipmentList2.getID());
        assertEquals("Designation2", equipmentList2.getDesignation());
        assertEquals(15, equipmentList2.getQuantity());
        assertEquals("Description2", equipmentList2.getDescription());
    }

    @Test
    void testGetEquipment() {
        // Simulate equipment data
        int equipmentId = 1;
        Equipment mockEquipment = new Equipment(equipmentId, "Designation1", 10, "Description1");

        // Mock the repository
        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(mockEquipment));

        // Test the method
        Equipment result = equipmentService.getEquipment(equipmentId);

        // Verify the result
        assertEquals(equipmentId, result.getID());
        assertEquals("Designation1", result.getDesignation());
        assertEquals(10, result.getQuantity());
        assertEquals("Description1", result.getDescription());
    }

    @Test
    void testUpdateEquipment() {
        // Simulate equipment data
        int equipmentId = 1;
        String newDesignation = "UpdatedDesignation";
        int newQuantity = 20;
        String newDescription = "UpdatedDescription";

        Equipment mockEquipment = new Equipment(equipmentId, "Designation1", 10, "Description1");

        // Mock the repository
        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(mockEquipment));
        when(equipmentRepository.save(Mockito.any(Equipment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test the method
        Equipment result = equipmentService.updateEquipment(equipmentId, newDesignation, newQuantity, newDescription);

        // Verify the result
        assertEquals(equipmentId, result.getID());
        assertEquals(newDesignation, result.getDesignation());
        assertEquals(newQuantity, result.getQuantity());
        assertEquals(newDescription, result.getDescription());
    }

    @Test
    void testAddEquipment() {
        // Simulate new equipment request
        NewEquipmentRequest newEquipmentRequest = new NewEquipmentRequest("NewDesignation", 15, "NewDescription");

        // Mock the repository
        when(equipmentRepository.save(Mockito.any(Equipment.class))).thenAnswer(invocation -> {
            Equipment savedEquipment = invocation.getArgument(0);
            savedEquipment.setID(1);
            return savedEquipment;
        });

        // Test the method
        Equipment result = equipmentService.addEquipment(newEquipmentRequest);

        // Verify the result
        assertEquals(1, result.getID());
        assertEquals("NewDesignation", result.getDesignation());
        assertEquals(15, result.getQuantity());
        assertEquals("NewDescription", result.getDescription());
    }

    @Test
    void testDeleteEquipment() {
        // Simulate existing equipment
        int existingEquipmentId = 1;
        Equipment existingEquipment = new Equipment(existingEquipmentId, "ExistingDesignation", 12, "ExistingDescription");

        // Mock the repository findById operation
        when(equipmentRepository.findById(existingEquipmentId)).thenReturn(Optional.of(existingEquipment));

        // Test the method
        equipmentService.deleteEquipment(existingEquipmentId);

        // Verify that the repository deleteById method was called
        verify(equipmentRepository, Mockito.times(1)).deleteById(existingEquipmentId);
    }

    @Test
    void testDeleteNonexistentEquipment() {
        // Simulate nonexistent equipment
        int nonexistentEquipmentId = 99;

        // Mock the repository findById operation
        when(equipmentRepository.findById(nonexistentEquipmentId)).thenReturn(Optional.empty());

        // Test the method and expect a ResponseStatusException
        assertThrows(ResponseStatusException.class, () -> equipmentService.deleteEquipment(nonexistentEquipmentId));

        // Verify that the repository deleteById method was not called
        verify(equipmentRepository, Mockito.never()).deleteById(nonexistentEquipmentId);
    }
}