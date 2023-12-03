package hu.NeptunApi.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    public void testNameValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Department department = new Department();
        department.setName(""); // Invalid department name

        // Act
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Assert
        assertEquals(1, violations.size()); // Only name validation error is expected
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Az name min 1 max 30 karakter")));
    }
    @Test
    public void testValidDepartment() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Department department = new Department();
        department.setName("ValidDepartment"); // Valid department name

        // Act
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Assert
        assertTrue(violations.isEmpty()); // No validation errors are expected
    }
}

