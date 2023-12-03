package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.DepartmentList;
import hu.NeptunApi.dto.NewDepartmentRequest;
import hu.NeptunApi.services.DepartmentService;
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
public class DepartmentControllersTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentControllers departmentControllers;

    @Test
    public void testGetDepartments() {
        // Arrange
        List<DepartmentList> expectedDepartments = Arrays.asList(
                new DepartmentList(),
                new DepartmentList()
        );
        when(departmentService.getDepartments()).thenReturn(expectedDepartments);

        // Act
        List<DepartmentList> actualDepartments = departmentControllers.getDepartments();

        // Assert
        assertEquals(expectedDepartments, actualDepartments);
    }

    @Test
    public void testGetDepartment() {
        // Arrange
        int departmentId = 1;
        Department expectedDepartment = new Department(departmentId, "Department1");
        when(departmentService.getDepartment(departmentId)).thenReturn(expectedDepartment);

        // Act
        Department actualDepartment = departmentControllers.getDepartment(departmentId);

        // Assert
        assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    public void testAddDepartment() {
        // Arrange
        NewDepartmentRequest newDepartmentRequest = new NewDepartmentRequest();

        // Act
        Department addedDepartment = departmentControllers.addDepartment(newDepartmentRequest);

        // Assert
        verify(departmentService, times(1)).addDepartment(newDepartmentRequest);
        assertEquals(HttpStatus.CREATED, ResponseEntity.status(HttpStatus.CREATED).build().getStatusCode());
    }

    @Test
    public void testUpdateDepartment() {
        // Arrange
        int departmentId = 1;
        Department updatedDepartment = new Department();
        updatedDepartment.setName("UpdatedDepartmentName"); // Set the updated department name

        departmentControllers.updateDepartment(departmentId, updatedDepartment);

        // Assert
        verify(departmentService, times(1)).updateDepartment(eq(departmentId), anyString());
    }


    @Test
    public void testDeleteDepartment() {
        // Arrange
        int departmentId = 1;

        // Act
        ResponseEntity<String> response = departmentControllers.deleteDepartment(departmentId);

        // Assert
        verify(departmentService, times(1)).deleteDepartment(eq(departmentId));
        assertEquals("Department delete successfully.", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}