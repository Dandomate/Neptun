package hu.NeptunApi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.DepartmentList;
import hu.NeptunApi.dto.NewDepartmentRequest;
import hu.NeptunApi.repositories.DepartmentRepository;
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
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void testGetDepartments() {
        // Simulate department data
        Object[] department1Data = {1, "Department1"};
        Object[] department2Data = {2, "Department2"};
        List<Object[]> mockData = List.of(department1Data, department2Data);

        // Mock the repository
        when(departmentRepository.getDepartments()).thenReturn(mockData);

        // Test the method
        List<DepartmentList> result = departmentService.getDepartments();

        // Verify the result
        assertEquals(2, result.size());

        DepartmentList departmentList1 = result.get(0);
        assertEquals(1, departmentList1.getID());
        assertEquals("Department1", departmentList1.getName());

        DepartmentList departmentList2 = result.get(1);
        assertEquals(2, departmentList2.getID());
        assertEquals("Department2", departmentList2.getName());
    }

    @Test
    void testGetDepartment() {
        // Simulate department data
        int departmentId = 1;
        Department mockDepartment = new Department(departmentId, "Department1");

        // Mock the repository
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(mockDepartment));

        // Test the method
        Department result = departmentService.getDepartment(departmentId);

        // Verify the result
        assertEquals(departmentId, result.getID());
        assertEquals("Department1", result.getName());
    }

    @Test
    void testUpdateDepartment() {
        // Simulate department data
        int departmentId = 1;
        String newName = "UpdatedDepartment";

        Department mockDepartment = new Department(departmentId, "Department1");

        // Mock the repository
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(mockDepartment));
        when(departmentRepository.save(Mockito.any(Department.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test the method
        Department result = departmentService.updateDepartment(departmentId, newName);

        // Verify the result
        assertEquals(departmentId, result.getID());
        assertEquals(newName, result.getName());
    }

    @Test
    void testAddDepartment() {
        // Simulate new department request
        NewDepartmentRequest newDepartmentRequest = new NewDepartmentRequest();
        newDepartmentRequest.setName("NewDepartment");

        // Mock the repository
        when(departmentRepository.save(Mockito.any(Department.class))).thenAnswer(invocation -> {
            Department savedDepartment = invocation.getArgument(0);
            savedDepartment.setID(1);
            return savedDepartment;
        });

        // Test the method
        Department result = departmentService.addDepartment(newDepartmentRequest);

        // Verify the result
        assertEquals(1, result.getID());
        assertEquals("NewDepartment", result.getName());
    }

    @Test
    void testDeleteDepartment() {
        // Simulate existing department
        int existingDepartmentId = 1;
        Department existingDepartment = new Department(existingDepartmentId, "ExistingDepartment");

        // Mock the repository findById operation
        when(departmentRepository.findById(existingDepartmentId)).thenReturn(Optional.of(existingDepartment));

        // Test the method
        departmentService.deleteDepartment(existingDepartmentId);

        // Verify that the repository deleteById method was called
        verify(departmentRepository, Mockito.times(1)).deleteById(existingDepartmentId);
    }

    @Test
    void testDeleteNonexistentDepartment() {
        // Simulate nonexistent department
        int nonexistentDepartmentId = 99;

        // Mock the repository findById operation
        when(departmentRepository.findById(nonexistentDepartmentId)).thenReturn(Optional.empty());

        // Test the method and expect a ResponseStatusException
        assertThrows(ResponseStatusException.class, () -> departmentService.deleteDepartment(nonexistentDepartmentId));
    }
}