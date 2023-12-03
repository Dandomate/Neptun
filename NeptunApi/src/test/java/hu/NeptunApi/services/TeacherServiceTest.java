package hu.NeptunApi.services;

import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.Teacher;
import hu.NeptunApi.dto.NewTeacherRequest;
import hu.NeptunApi.repositories.DepartmentRepository;
import hu.NeptunApi.repositories.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void testGetTeachers() {
        // Simulate the repository nativeFindTeachers operation
        List<Teacher> mockTeachers = new ArrayList<>();
        when(teacherRepository.nativeFindTeachers()).thenReturn(mockTeachers);

        // Test the method
        List<Teacher> result = teacherService.getTeachers();

        // Verify that the expected result matches the actual result
        assertEquals(mockTeachers, result);
    }

    @Test
    void testGetTeacher() {
        // Simulate getting a teacher by ID
        int teacherId = 1;
        Teacher mockTeacher = new Teacher();
        mockTeacher.setID(teacherId);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(mockTeacher));

        // Test the method
        Teacher result = teacherService.getTeacher(teacherId);

        // Verify that the expected result matches the actual result
        assertEquals(teacherId, result.getID());
    }

    @Test
    void testDeleteTeacher() {
        // Simulate the existence of a teacher
        Teacher existingTeacher = new Teacher();
        existingTeacher.setID(1);

        // Simulate the repository findById operation
        when(teacherRepository.findById(1)).thenReturn(Optional.of(existingTeacher));

        // Test the method
        teacherService.deleteTeacher(1);

        // Verify that the repository deleteById method was called
        verify(teacherRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetTeacherDetails() {
        // Simulate getting teacher details by ID
        int teacherId = 1;
        Teacher mockTeacher = new Teacher();
        mockTeacher.setID(teacherId);
        mockTeacher.setName("John Doe");

        Department mockDepartment = new Department();
        mockDepartment.setName("Math Department");
        mockTeacher.setDepartment(mockDepartment);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(mockTeacher));

        // Test the method
        Map<String, String> result = teacherService.getTeacherDetails(teacherId);

        // Verify that the expected result matches the actual result
        assertNotNull(result);
        assertEquals("John Doe", result.get("teacherName"));
        assertEquals("Math Department", result.get("departmentName"));
    }

    @Test
    void testAddTeacherToExistingDepartment() {
        // Simulate adding a teacher to an existing department
        NewTeacherRequest teacherRequest = new NewTeacherRequest();
        teacherRequest.setName("John Doe");
        teacherRequest.setNeptun_code("JD123");
        teacherRequest.setDepartment_ID(1);

        Department mockDepartment = new Department();
        mockDepartment.setID(1);

        when(departmentRepository.findById(teacherRequest.getDepartment_ID())).thenReturn(Optional.of(mockDepartment));

        // Test the method
        teacherService.addTeacherToExistingDepartment(teacherRequest);

        // Verify that the repository save method was called
        verify(teacherRepository, times(1)).save(Mockito.any(Teacher.class));
        // Verify that the teacher was added to the department
        assertTrue(mockDepartment.getTeachers().size() > 0);
    }

    @Test
    void testUpdateTeacher() {
        // Simulate updating a teacher's name
        int teacherId = 1;
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated Name");

        Teacher mockTeacher = new Teacher();
        mockTeacher.setID(teacherId);
        mockTeacher.setName("John Doe");

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(mockTeacher));
        when(teacherRepository.save(Mockito.any(Teacher.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test the method
        Teacher result = teacherService.updateTeacher(teacherId, updates);

        // Verify that the expected result matches the actual result
        assertEquals(teacherId, result.getID());
        assertEquals("Updated Name", result.getName());
    }

    @Test
    void testUpdateTeacherDepartment() {
        // Simulate updating a teacher's department
        int teacherId = 1;
        int newDepartmentId = 2;

        Teacher mockTeacher = new Teacher();
        mockTeacher.setID(teacherId);

        Department mockDepartment = new Department();
        mockDepartment.setID(newDepartmentId);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(mockTeacher));
        when(departmentRepository.findById(newDepartmentId)).thenReturn(Optional.of(mockDepartment));
        when(teacherRepository.save(Mockito.any(Teacher.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test the method
        Teacher result = teacherService.updateTeacherDepartment(teacherId, newDepartmentId);

        // Verify that the expected result matches the actual result
        assertEquals(teacherId, result.getID());
        assertEquals(mockDepartment, result.getDepartment());
    }
}

