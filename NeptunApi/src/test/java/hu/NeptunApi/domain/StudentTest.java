package hu.NeptunApi.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    public void testNameValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Student student = new Student();
        student.setName("Sh"); // Invalid name

        // Act
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A név 3-30 közötti karakter legyen")));
    }

    @Test
    public void testBirthDateValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Student student = new Student();
        student.setBirth_date(""); // Invalid birth date

        // Act
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A születés 1-30 közötti karakter legyen igy add meg: 2001-10-10")));
    }

    @Test
    public void testNeptunCodeValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Student student = new Student();
        student.setNeptun_code(""); // Invalid neptun code

        // Act
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A neptun 1-30 közötti karakter legyen")));
    }
}