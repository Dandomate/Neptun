package hu.NeptunApi.domain;

import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ClassRoomTest {
    @Test
    public void testDoorValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        ClassRoom classRoom = new ClassRoom();
        classRoom.setDoor("TooLongDoorName"); // Invalid door name
        classRoom.setSpace(0); // Invalid space value

        // Act
        Set<ConstraintViolation<ClassRoom>> violations = validator.validate(classRoom);

        // Assert
        assertEquals(2, violations.size());  // Itt 2-re van várva
        // Ebben a tesztben mindkét hiba megjelenik
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Az ajtoszám min 1 max 10 karakter")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Érték 1 nél kisebb")));
    }

    @Test
    public void testSpaceValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Arrange
        ClassRoom classRoom = new ClassRoom();
        classRoom.setSpace(0); // Invalid space value

        // Act
        Set<ConstraintViolation<ClassRoom>> violations = validator.validate(classRoom);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("Érték 1 nél kisebb", violations.iterator().next().getMessage());
    }
}