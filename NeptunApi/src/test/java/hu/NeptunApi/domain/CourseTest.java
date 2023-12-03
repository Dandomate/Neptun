package hu.NeptunApi.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    public void testNameValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Course course = new Course();
        course.setName(""); // Invalid name

        // Act
        Set<ConstraintViolation<Course>> violations = validator.validate(course);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Az name min 1 max 10 karakter")));
    }

    @Test
    public void testDescriptionValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Course course = new Course();
        course.setDescription(""); // Invalid description

        // Act
        Set<ConstraintViolation<Course>> violations = validator.validate(course);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Az leírás min 0 max 30 karakter")));
    }

    @Test
    public void testDayValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Course course = new Course();
        course.setDay("InvalidDayName123"); // Invalid day

        // Act
        Set<ConstraintViolation<Course>> violations = validator.validate(course);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A nap 1-10 közötti karakter")));
    }
}