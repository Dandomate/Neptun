package hu.NeptunApi.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    public void testNameValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Teacher teacher = new Teacher();
        teacher.setName("Sh"); // Invalid name

        // Act
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Your Name min 5 characters and max 30 characters")));
    }

    @Test
    public void testNeptunCodeValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        Teacher teacher = new Teacher();
        teacher.setNeptun_code(""); // Invalid neptun code

        // Act
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);

        // Assert
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Your Neptune code min 5 characters and max 30 characters")));
    }
}