package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.ClassRoom;
import hu.NeptunApi.domain.Teacher;
import hu.NeptunApi.dto.NewTeacherRequest;
import hu.NeptunApi.services.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @Test
    public void testDeleteTeacher() {
        // Arrange
        int teacherId = 1;

        // Act
        ResponseEntity<String> responseEntity = teacherController.deleteTeacher(teacherId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the deleteTeacher method of teacherService was called with the correct teacherId
        verify(teacherService, times(1)).deleteTeacher(eq(teacherId));
    }

    @Test
    public void testGetTeacherDetails() {
        // Arrange
        int teacherId = 1;
        Map<String, String> expectedDetails = new HashMap<>();
        expectedDetails.put("key1", "value1");
        when(teacherService.getTeacherDetails(teacherId)).thenReturn(expectedDetails);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = teacherController.getTeacherDetails(teacherId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDetails, responseEntity.getBody());
    }

    @Test
    public void testAddTeacherToExistingDepartment() {
        // Arrange
        NewTeacherRequest newTeacherRequest = new NewTeacherRequest();

        // Act
        ResponseEntity<String> responseEntity = teacherController.addTeacherToExistingDepartment(newTeacherRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Teacher added successfully.", responseEntity.getBody());
    }


    @Test
    public void testGetTeachers() {
        // Arrange
        List<Teacher> expectedTeachers = List.of(new Teacher(), new Teacher());
        when(teacherService.getTeachers()).thenReturn(expectedTeachers);

        // Act
        List<Teacher> actualTeachers = teacherController.getTeachers();

        // Assert
        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    public void testGetTeacher() {
        // Arrange
        int teacherId = 1;
        Teacher expectedTeacher = new Teacher();
        when(teacherService.getTeacher(teacherId)).thenReturn(expectedTeacher);

        // Act
        Teacher actualTeacher = teacherController.getTeacher(teacherId);

        // Assert
        assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    public void testUpdateTeacherName() {
        // Arrange
        int teacherId = 1;
        String updatedName = "Valaki";
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", updatedName);

        // Itt beállítjuk, hogy a service.updateTeacher hívásnál visszaadjon egy példa Teacher objektumot
        Teacher expectedUpdatedTeacher = new Teacher();
        expectedUpdatedTeacher.setName(updatedName);
        when(teacherService.updateTeacher(eq(teacherId), eq(updates))).thenReturn(expectedUpdatedTeacher);

        // Act
        ResponseEntity<String> response = teacherController.updateTeacher(teacherId, updates);

        // Assert
        // Ellenőrizzük, hogy a service.updateTeacher metódus pontosan egyszer hívódott-e meg az elvárt paraméterekkel
        verify(teacherService, times(1)).updateTeacher(eq(teacherId), eq(updates));

        // Ellenőrizzük, hogy a válasz tartalmazza az elvárt szöveget
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Teacher updated successfully. New name: " + expectedUpdatedTeacher.getName(), response.getBody());
    }
    @Test
    public void testUpdateTeacherDepartment() {
        // Arrange
        int teacherId = 1;
        Map<String, Object> payload = Map.of("department_ID", 2);

        // Act
        ResponseEntity<Teacher> responseEntity = teacherController.updateTeacherDepartment(teacherId, payload);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Add further assertions based on the expected behavior of your updateTeacherDepartment method
    }
}