package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.Course;
import hu.NeptunApi.domain.StudentCourseList;
import hu.NeptunApi.domain.TeacherCourseList;
import hu.NeptunApi.dto.NewCourseRequest;
import hu.NeptunApi.services.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Test
    public void testGetCourses() {
        // Arrange
        List<Course> expectedCourses = Arrays.asList(
                new Course(),
                new Course()
        );
        when(courseService.getCourse()).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = courseController.getCourses();

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }

    @Test
    public void testAddCourse() {
        // Arrange
        NewCourseRequest newCourseRequest = new NewCourseRequest();

        // Act
        ResponseEntity<String> responseEntity = courseController.addCourse(newCourseRequest);

        // Assert
        verify(courseService, times(1)).addCourse(newCourseRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Course added successfully.", responseEntity.getBody());
    }

    @Test
    public void testUpdateCourseName() {
        // Arrange
        int courseId = 1;
        Course updatedCourse = new Course();
        updatedCourse.setName("UpdateCourseName");
        // Act
        Course result = courseController.updateCourse(courseId, updatedCourse);

        // Assert
        verify(courseService, times(1)).updateCourse(eq(courseId), anyString());
        // Add additional assertions if needed
    }

    @Test
    public void testDeleteCourse() {
        // Arrange
        int courseId = 1;

        // Act
        ResponseEntity<String> responseEntity = courseController.deleteCourse(courseId);

        // Assert
        verify(courseService, times(1)).deleteCourse(eq(courseId));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Course delete successfully.", responseEntity.getBody());
    }

    @Test
    public void testGetStudentCourseList() {
        // Arrange
        String neptunCode = "NEPTUN123";
        String days = "Monday";
        List<StudentCourseList> expectedList = new ArrayList<>(); // Add some test data

        // Itt beállítjuk, hogy a service.getStudentCourseList híváskor visszaadjon egy példa listát
        when(courseService.getStudentCourseList(eq(neptunCode), eq(days))).thenReturn(expectedList);

        // Act
        List<StudentCourseList> result = courseController.getStudentCourseList(neptunCode, days);

        // Assert
        // Ellenőrizzük, hogy a service.getStudentCourseList metódus pontosan egyszer hívódott-e meg az elvárt paraméterekkel
        verify(courseService, times(1)).getStudentCourseList(eq(neptunCode), eq(days));

        // Ellenőrizzük, hogy a visszatérési érték megegyezik-e az elvárttal
        assertEquals(expectedList, result);
    }

    @Test
    public void testGetTeacherCourseList() {
        // Arrange
        String neptunCode = "NEPTUN456";
        String days = "Tuesday";
        List<TeacherCourseList> expectedList = new ArrayList<>();

        // Itt beállítjuk, hogy a service.getTeacherCourseList híváskor visszaadjon egy példa listát
        when(courseService.getTeacherCourseList(eq(neptunCode), eq(days))).thenReturn(expectedList);

        // Act
        List<TeacherCourseList> result = courseController.getTeacherCourseList(neptunCode, days);

        // Assert
        // Ellenőrizzük, hogy a service.getTeacherCourseList metódus pontosan egyszer hívódott-e meg az elvárt paraméterekkel
        verify(courseService, times(1)).getTeacherCourseList(eq(neptunCode), eq(days));

        // Ellenőrizzük, hogy a visszatérési érték megegyezik-e az elvárttal
        assertEquals(expectedList, result);
    }

    @Test
    public void testGetCourse() {
        // Arrange
        int courseId = 1;
        Course expectedCourse = new Course();

        // Itt beállítjuk, hogy a service.getCourse híváskor visszaadjon egy példa Course objektumot
        when(courseService.getCourse(eq(courseId))).thenReturn(expectedCourse);

        // Act
        Course result = courseController.getCourse(courseId);

        // Assert
        // Ellenőrizzük, hogy a service.getCourse metódus pontosan egyszer hívódott-e meg az elvárt paraméterekkel
        verify(courseService, times(1)).getCourse(eq(courseId));

        // Ellenőrizzük, hogy a visszatérési érték megegyezik-e az elvárttal
        assertEquals(expectedCourse, result);
    }

}