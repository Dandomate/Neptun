package hu.NeptunApi.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    @Test
    public void testDesignationValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Equipment equipment = new Equipment();
        equipment.setDesignation(""); // Invalid designation

        // Act
        Set<ConstraintViolation<Equipment>> violations = validator.validate(equipment);

        // Assert
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A megnevezés min 1 max 30 karakter")));
    }

    @Test
    public void testQuantityValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Equipment equipment = new Equipment();
        equipment.setQuantity(0); // Invalid quantity value

        // Act
        Set<ConstraintViolation<Equipment>> violations = validator.validate(equipment);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("érték 1 nél kisebb", violations.iterator().next().getMessage());
    }

    @Test
    public void testQuantityInvalidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Equipment equipment = new Equipment();
        equipment.setQuantity(100); // Invalid quantity value

        // Act
        Set<ConstraintViolation<Equipment>> violations = validator.validate(equipment);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("érték 30 nál nagyobb", violations.iterator().next().getMessage());
    }

    @Test
    public void testDescriptionValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Equipment equipment = new Equipment();
        equipment.setDescription(""); // Invalid description

        // Act
        Set<ConstraintViolation<Equipment>> violations = validator.validate(equipment);

        // Assert
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("A megnevezés min 1 max 100 karakter")));
    }
}