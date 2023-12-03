package hu.NeptunApi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.StudentList;
import hu.NeptunApi.dto.NewStudentRequest;
import hu.NeptunApi.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void testGetStudent() {
        // Simulate a student retrieved from the database
        int studentId = 1;
        Student mockStudent = new Student(studentId, "John Doe", "2000-01-01", "NEP123");

        // Mock the repository findById operation
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));

        // Test the method
        Student result = studentService.getStudent(studentId);

        // Verify that the result matches the expected student
        assertEquals(studentId, result.getID());
        assertEquals("John Doe", result.getName());
        assertEquals("2000-01-01", result.getBirth_date());
        assertEquals("NEP123", result.getNeptun_code());
    }

    @Test
    void testGetStudents() {
        // Simulate a list of students returned from the repository
        Object[] student1Data = {1, "John Doe", "2000-01-01", "NEP123"};
        Object[] student2Data = {2, "Jane Doe", "1999-12-31", "NEP456"};
        List<Object[]> mockData = Arrays.asList(student1Data, student2Data);

        // Mock the repository getStudents operation
        when(studentRepository.getStudents()).thenReturn(mockData);

        // Test the method
        List<StudentList> result = studentService.getStudents();

        // Verify that the result matches the expected students
        assertEquals(2, result.size());

        StudentList studentList1 = result.get(0);
        assertEquals(1, studentList1.getID());
        assertEquals("John Doe", studentList1.getName());
        assertEquals("2000-01-01", studentList1.getBirth_date());
        assertEquals("NEP123", studentList1.getNeptun_code());

        StudentList studentList2 = result.get(1);
        assertEquals(2, studentList2.getID());
        assertEquals("Jane Doe", studentList2.getName());
        assertEquals("1999-12-31", studentList2.getBirth_date());
        assertEquals("NEP456", studentList2.getNeptun_code());
    }

    @Test
    void testUpdateStudent() {
        // Simulate updating an existing student
        int studentId = 1;
        String newName = "Updated Name";
        String newBirthDate = "2000-02-02";
        String newNeptunCode = "NEP789";

        Student mockStudent = new Student(studentId, "John Doe", "2000-01-01", "NEP123");
        Student updatedStudent = new Student(studentId, newName, newBirthDate, newNeptunCode);

        // Mock the repository findById and save operations
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));
        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);

        // Test the method
        Student result = studentService.updateStudent(studentId, newName, newBirthDate, newNeptunCode);

        // Verify that the result matches the updated student
        assertEquals(studentId, result.getID());
        assertEquals(newName, result.getName());
        assertEquals(newBirthDate, result.getBirth_date());
        assertEquals(newNeptunCode, result.getNeptun_code());
    }

    @Test
    void testAddStudent() {
        // Simulate adding a new student
        NewStudentRequest newStudentRequest = new NewStudentRequest();
        newStudentRequest.setName("New Student");
        newStudentRequest.setBirth_date("2001-01-01");
        newStudentRequest.setNeptun_code("NEP999");

        // Mock the repository save operation
        when(studentRepository.save(Mockito.any(Student.class))).thenAnswer(invocation -> {
            Student savedStudent = invocation.getArgument(0);
            savedStudent.setID(1);  // Simulated ID setting
            return savedStudent;
        });

        // Test the method
        Student result = studentService.addStudent(newStudentRequest);

        // Verify that the result matches the expected new student
        assertEquals(1, result.getID());
        assertEquals("New Student", result.getName());
        assertEquals("2001-01-01", result.getBirth_date());
        assertEquals("NEP999", result.getNeptun_code());

        // Verify that the save method was called
        verify(studentRepository, Mockito.times(1)).save(Mockito.any(Student.class));
    }

    @Test
    void testDeleteStudent() {
        // Simulate an existing student
        Student existingStudent = new Student();
        existingStudent.setID(1);

        // Mock the repository findById operation
        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));

        // Test the method
        studentService.deleteStudent(1);

        // Verify that the repository deleteById method was called
        verify(studentRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void testDeleteNonexistentStudent() {
        // Simulate nonexistent student
        int nonexistentStudentId = 99;

        // Mock the repository findById operation
        when(studentRepository.findById(nonexistentStudentId)).thenReturn(Optional.empty());

        // Test the method and expect a ResponseStatusException
        assertThrows(ResponseStatusException.class, () -> studentService.deleteStudent(nonexistentStudentId));

        // Verify that the repository deleteById method was not called
        verify(studentRepository, Mockito.never()).deleteById(nonexistentStudentId);
    }
}